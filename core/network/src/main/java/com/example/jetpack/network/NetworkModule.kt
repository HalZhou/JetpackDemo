package com.example.jetpack.network

import com.example.jetpack.network.cookies.CustomCookieJar
import com.example.jetpack.network.interceptors.HeaderInterceptor
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

const val NETWORK_CONNECT_TIMEOUT = 10L
const val NETWORK_READ_TIMEOUT = 10L
@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    // 提供实体类
    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providesOkHttpClient(cookieJar : CustomCookieJar): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(NETWORK_CONNECT_TIMEOUT,TimeUnit.SECONDS)
            .readTimeout(NETWORK_READ_TIMEOUT,TimeUnit.SECONDS)
            .addInterceptor(HeaderInterceptor())
            .addInterceptor(HttpLoggingInterceptor().apply {
                setLevel(HttpLoggingInterceptor.Level.BASIC)
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