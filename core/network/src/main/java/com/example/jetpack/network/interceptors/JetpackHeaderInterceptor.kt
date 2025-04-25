package com.example.jetpack.network.interceptors

import android.app.AlertDialog
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.os.Message
import com.example.jetpack.network.exception.ApiException
import com.example.jetpack.network.model.BaseResponse
import com.example.jetpack.network.model.ConfirmationDto
import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.atomic.AtomicReference

class JetpackHeaderInterceptor : Interceptor {

    private var mContext: Context? = null
    private val mHandler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            val confirmation = msg.obj
            if (confirmation is ConfirmationDto) {
                val option = confirmation.options.firstOrNull()
                AlertDialog.Builder(mContext)
                    .setTitle("提示信息")
                    .setMessage(confirmation.message)
                    .setNegativeButton("取消") { dialog, _ ->
                        dialog.dismiss()
                        intercepted.set(false)
                    }
                    .setPositiveButton(option?.message ?: "确定") { dialog, _ ->
                        val newConfirmation = confirmation.apply {
                            confirmedOption = option
                        }
                        resultFromIntercepted.set(newConfirmation)
                        intercepted.set(false)
                        dialog.dismiss()
                    }
                    .create()
                    .show()
            }
        }
    }

    // 拦截后的处理结果（拒绝，重新请求，原数据返回）
    private val resultFromIntercepted = AtomicReference<ConfirmationDto>(null)

    // 标识拦截处理
    private val intercepted: AtomicBoolean = AtomicBoolean(false)

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)

        if (response.code == 200) {
            // 成功
            return response
        }
        if (response.code == 403) {
            // 重新登录
            throw ApiException("${response.code}","权限已过期，请重新登录")
        }
        if (response.code == 503) {
            throw ApiException("${response.code}", "系统正在升级中...")
        }
        if (response.code == 400) {
            val data = fromJsonToResponse(response) ?: return response
            val error = data.errors.firstOrNull()
            if (error != null) {
                throw ApiException(error.code,error.message)
            }
            val confirmation = data.confirmations.firstOrNull()
            if (confirmation != null) {
                val result = mHandler.sendMessage(Message().apply {
                    this.obj = confirmation
                })
                if (result) {
                    intercepted.set(true)
                    // 开始处理弹窗
                    while (intercepted.get()) {
                        // 等待
                    }
                    val alertDialogResponse = resultFromIntercepted.get()
                        ?: // 取消
                        return response

                    // 确定重新请求
                    val requestBody = request.body
                    val requestParams = fromJsonToRequest(request) ?: return response
                    requestParams.confirmations.apply {
                        clear()
                        add(alertDialogResponse)
                    }
                    val newRequestBody =
                        Gson().toJson(requestParams).toRequestBody(requestBody?.contentType())
                    val newRequest = chain.request()
                        .newBuilder()
                        .method(request.method, newRequestBody)
                        .build()
                    return chain.proceed(newRequest)
                }
            }
        }
        return response
    }

    private fun fromJsonToResponse(response: Response): BaseResponse<Any>? {
        return try {
            Gson().fromJson<BaseResponse<Any>>(
                response.body?.charStream(),
                BaseResponse::class.java
            )
        } catch (e: Exception) {
            null
        }
    }

    private fun fromJsonToRequest(request: Request): BaseResponse<Any>? {
        return try {
            Gson().fromJson<BaseResponse<Any>>(
                request.body.toString(),
                BaseResponse::class.java
            )
        } catch (e: Exception) {
            null
        }
    }
}