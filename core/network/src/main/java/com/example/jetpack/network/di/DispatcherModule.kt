package com.example.jetpack.network.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
object DispatcherModule {

    @Provides
    fun provideDefaultDispatcher() : CoroutineDispatcher = Dispatchers.Default

    @Provides
    fun provideIoDispatcher() : CoroutineDispatcher = Dispatchers.IO

    @Provides
    fun provideMainDispatcher() : CoroutineDispatcher = Dispatchers.Main
}


@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Dispatcher(val niaDispatcher : NiaDispatcher)

enum class NiaDispatcher {
    Default,
    IO,
    Main
}