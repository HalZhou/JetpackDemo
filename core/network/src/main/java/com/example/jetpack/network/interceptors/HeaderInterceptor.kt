package com.example.jetpack.network.interceptors

import com.example.jetpack.common.utils.AppUtils
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val terminalTypeString =
            "Android[MODEL=${AppUtils.getSystemModel()},MANUFACTURER=${AppUtils.getMANUFACTURER()},OSVERSION=${AppUtils.getSystemVersion()},APPVERSION=${AppUtils.getAppVersionName()}]"

        val request = chain.request().newBuilder()
            .addHeader("ENOCH_TERMINAL", terminalTypeString)
            .build()
        return chain.proceed(request)
    }
}