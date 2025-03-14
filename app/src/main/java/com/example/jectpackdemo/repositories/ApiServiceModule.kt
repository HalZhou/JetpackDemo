package com.example.jectpackdemo.repositories

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class ApiServiceModule {

    @Provides
    @Singleton
    fun provideJetpackApiService(retrofit : Retrofit) : JetpackApiService {
        return retrofit.create(JetpackApiService::class.java)
    }
}