package com.example.jetpack.common.utils

import android.view.Gravity
import android.widget.Toast

// 用于解决Android中多次调用原生Toast导致时长叠加的问题
object ToastUtils {

    @JvmStatic
    private var mToast: Toast? = null

    private var mText: String? = null
    private fun initToast(message : String) {
        mToast = Toast.makeText(AppUtils.getContext(),message,Toast.LENGTH_SHORT).apply {
            this.setGravity(Gravity.TOP, 0, 100)
        }
    }

    @JvmStatic
    fun show(message: String) {
        // 相同的文案不重新显示
        if (mText == message) return
        if (mToast == null) {
            initToast(message)
        }
        mToast?.setText(message)
        mToast?.show()
        mText = message
    }
}