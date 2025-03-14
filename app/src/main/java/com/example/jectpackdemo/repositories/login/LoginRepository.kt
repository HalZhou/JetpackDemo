package com.example.jectpackdemo.repositories.login

import com.example.jetpack.network.di.Dispatcher
import com.example.jetpack.network.di.NiaDispatcher
import com.example.jectpackdemo.repositories.JetpackApiService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val apiService: JetpackApiService,
    @com.example.jetpack.network.di.Dispatcher(com.example.jetpack.network.di.NiaDispatcher.IO) val ioDispatcher: CoroutineDispatcher
) {

    suspend fun toLogin(request: String) = withContext(ioDispatcher) {
        apiService.login(request)
    }
}