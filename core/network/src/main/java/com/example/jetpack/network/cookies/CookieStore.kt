package com.example.jetpack.network.cookies

import okhttp3.Cookie
import okhttp3.HttpUrl

interface CookieStore {

    /** 加载url所有的cookie */
    fun loadForRequest(url: HttpUrl): List<Cookie>

    /** 保存url对应所有cookie */
    fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>)

    /** 获取当前所有保存的cookie */
    fun getAllCookies(): List<Cookie>

    /** 根据url和cookie移除对应的cookie */
    fun removeCookie(url: HttpUrl, cookie: Cookie): Boolean

    /** 移除所有的cookie */
    fun removeAllCookies()
}