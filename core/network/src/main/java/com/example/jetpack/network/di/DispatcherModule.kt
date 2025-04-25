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

    @Dispatcher(NiaDispatcher.Default)
    @Provides
    fun provideDefaultDispatcher() : CoroutineDispatcher = Dispatchers.Default

    @Dispatcher(NiaDispatcher.IO)
    @Provides
    fun provideIoDispatcher() : CoroutineDispatcher = Dispatchers.IO

    @Dispatcher(NiaDispatcher.Main)
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