package com.example.jetpack.network.interceptors

import com.example.jetpack.network.micspyUrl
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.Interceptor
import okhttp3.Response

val domains = hashMapOf<String,String>(
    "enocbootflow" to micspyUrl
)
class DomainInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val serviceName =
            request.url.encodedPathSegments.filterNot { it.isEmpty() }.firstOrNull()
        val shouldHostUrl = domains.getOrDefault(serviceName, null)?.toHttpUrlOrNull()
        if (shouldHostUrl != null) {
            val newUrl = request.url.newBuilder()
                .scheme(shouldHostUrl.scheme)
                .host(shouldHostUrl.host)
                .port(shouldHostUrl.port)
                .build()
            val newRequest = request.newBuilder().url(newUrl).build()
            return chain.proceed(newRequest)
        }
        return chain.proceed(chain.request())
    }
}