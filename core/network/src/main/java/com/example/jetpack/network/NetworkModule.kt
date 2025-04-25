package com.example.jetpack.network

import com.example.jetpack.network.cookies.CustomCookieJar
import com.example.jetpack.network.interceptors.DomainInterceptor
import com.example.jetpack.network.interceptors.HeaderInterceptor
import com.example.jetpack.network.interceptors.JetpackHeaderInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton

const val debug = true
// 轻量化
val micspyUrl : String get() = if (debug) "https://micspyd.enoch-car.com/" else "https://micspy.enoch-car.com/"

val oaUrl : String get() = if (debug) "https://enocloudd.enoch-car.com/" else "https://enocloud.enoch-car.com/"

const val NETWORK_CONNECT_TIMEOUT = 10L
const val NETWORK_READ_TIMEOUT = 10L
@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    // 提供实体类
    @Singleton
    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(oaUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun providesOkHttpClient(cookieJar : CustomCookieJar): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(NETWORK_CONNECT_TIMEOUT,TimeUnit.SECONDS)
            .readTimeout(NETWORK_READ_TIMEOUT,TimeUnit.SECONDS)
            .addInterceptor(HeaderInterceptor())
            .addInterceptor(DomainInterceptor())
            .addInterceptor(JetpackHeaderInterceptor())
            .addInterceptor(HttpLoggingInterceptor().apply {
                setLevel(HttpLoggingInterceptor.Level.BODY)
            })
            .cookieJar(cookieJar)
            .build()
    }
}

// 为同一类型提供不同的实现
// 喷涂OA的网络请求类
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AuthOkhttpInterceptorClient

// 透明车间的网络请求类
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class OtherOkhttpInterceptorClient