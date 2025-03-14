package com.example.jetpack.database.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.example.jetpack.database.AppDatabase
import com.example.jetpack.database.dao.CookieDao

@Module
@InstallIn(SingletonComponent::class)
internal object DaoModule {

    @Provides
    fun provideCookieDao(database : AppDatabase) : CookieDao {
        return database.cookieDao()
    }
}