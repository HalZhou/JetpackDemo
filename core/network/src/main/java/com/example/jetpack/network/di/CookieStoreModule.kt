package com.example.jetpack.network.di

import com.example.jetpack.network.cookies.CookieStore
import com.example.jetpack.network.cookies.PersistentCookieStore
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class CookieStoreModule {

    @Binds
    abstract fun providePersistentCookieStore(persistentCookieStore: PersistentCookieStore) : CookieStore
}