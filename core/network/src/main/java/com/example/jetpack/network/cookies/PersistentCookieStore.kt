package com.example.jetpack.network.cookies

import com.example.jetpack.database.dao.CookieDao
import com.example.jetpack.database.entity.CookieEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Cookie
import okhttp3.HttpUrl
import java.util.concurrent.ConcurrentHashMap
import javax.inject.Inject


class PersistentCookieStore @Inject constructor(private val dao: CookieDao) : CookieStore {

    /**
     * 存储格式<<cookie.name,cookie>>
     */
    private val dataCookies: ConcurrentHashMap<String, Cookie> = ConcurrentHashMap()

    private val coroutineScope = MainScope()

    init {
        coroutineScope.launch {
            val temp = getAllCookiesFromDatabase()
            for (cookie in temp) {
                if (cookie.isExpiredAt()) {
                    deleteCookiesFromDatabase(listOf(cookie))
                } else {
                    dataCookies[cookie.name] = cookie
                }
            }
        }
    }

    override fun loadForRequest(url: HttpUrl): List<Cookie> {
        val list = arrayListOf<Cookie>()
        for ((name, cookie) in dataCookies) {
            if (cookie.isExpiredAt()) {
                removeCookie(url, cookie)
            } else {
                list.add(cookie)
            }
        }
        return list
    }

    override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
        coroutineScope.launch {
            val result =
                cookies.filterNot { it.isExpiredAt() }.filterNot { it.value.isNullOrEmpty() }
            result.forEach { cookie ->
                dataCookies[cookie.name] = cookie
            }
            insertCookiesFromDatabase(result)
        }
    }

    override fun getAllCookies(): List<Cookie> {
        return dataCookies.values.toList()
    }

    override fun removeCookie(url: HttpUrl, cookie: Cookie): Boolean {
        val value = dataCookies.remove(cookie.name)
        coroutineScope.launch {
            deleteCookiesFromDatabase(listOf(cookie))
        }
        return value != null
    }

    override fun removeAllCookies() {
        coroutineScope.launch {
            deleteCookiesFromDatabase(dataCookies.values.toList())
        }
        dataCookies.clear()
    }


    private suspend fun getAllCookiesFromDatabase() : List<Cookie> = withContext(Dispatchers.IO) {
        dao.getAllCookies().map { it.toCookie() }
    }

    private suspend fun insertCookiesFromDatabase(cookies: List<Cookie>) =
        withContext(Dispatchers.IO) {
            dao.insertCookies(cookies.map { it.toEntity() })
        }

    private suspend fun deleteCookiesFromDatabase(cookies: List<Cookie>) =
        withContext(Dispatchers.IO) {
            val list = cookies.toList()
            dao.deleteCookies(list.map { it.toEntity() })
        }

    private fun CookieEntity.toCookie() : Cookie {
        val builder = Cookie.Builder().apply {
            name(name)
            value(value)
            expiresAt(expiresAt)
            if (httpOnly) {
                hostOnlyDomain(domain)
            } else {
                domain(domain)
            }
            path(path)
            if (secure) {
                secure()
            }
            if (httpOnly) {
                httpOnly()
            }
        }
        return builder.build()
    }

    private fun Cookie.toEntity(): CookieEntity {
        return CookieEntity(
            name = name,
            value = value,
            expiresAt = expiresAt,
            domain = domain,
            path = path,
            secure = secure,
            httpOnly = httpOnly,
            persistent = persistent
        )
    }

    private fun Cookie.isExpiredAt(): Boolean {
        return expiresAt < System.currentTimeMillis()
    }
}