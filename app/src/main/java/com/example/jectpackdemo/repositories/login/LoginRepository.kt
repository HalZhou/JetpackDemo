package com.example.jectpackdemo.repositories.login

import com.example.jectpackdemo.bean.request.LoginRequest
import com.example.jetpack.network.model.toCreateRequest
import com.example.jectpackdemo.repositories.JetpackApiService
import com.example.jetpack.network.di.Dispatcher
import com.example.jetpack.network.di.NiaDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val apiService: JetpackApiService,
    @Dispatcher(NiaDispatcher.IO) val ioDispatcher: CoroutineDispatcher
) {

    suspend fun toLogin(request: LoginRequest) = withContext(ioDispatcher) {
        apiService.login(request.toCreateRequest())
    }

    suspend fun getUser() = withContext(ioDispatcher) {
        apiService.getUser()
    }

    suspend fun logout() = withContext(ioDispatcher) {

    }

    suspend fun toChangePassword() = withContext(ioDispatcher) {

    }

    suspend fun toRegister() = withContext(ioDispatcher) {

    }

}