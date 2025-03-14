package com.example.jetpack.network.cookies

import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl
import javax.inject.Inject

class CustomCookieJar @Inject constructor(private val cookieStore: CookieStore) : CookieJar {
    override fun loadForRequest(url: HttpUrl): List<Cookie> {
        return cookieStore.loadForRequest(url)
    }

    override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
        cookieStore.saveFromResponse(url, cookies)
    }

    fun removeAllCookies() {
        cookieStore.removeAllCookies()
    }
}