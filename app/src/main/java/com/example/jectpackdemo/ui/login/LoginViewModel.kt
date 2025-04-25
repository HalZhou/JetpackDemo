package com.example.jectpackdemo.ui.login

import android.content.Context
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jectpackdemo.base.tryCatchFromResponse
import com.example.jectpackdemo.bean.dto.UserDto
import com.example.jectpackdemo.bean.request.LoginRequest
import com.example.jectpackdemo.repositories.login.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle = SavedStateHandle(),
    @ApplicationContext private val context: Context,
    private val loginRepository: LoginRepository
) : ViewModel() {

    private val _username = MutableStateFlow("")
    val username: StateFlow<String> = _username.asStateFlow()
    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password.asStateFlow()


    private val _logged = MutableStateFlow<UserDto?>(null)
    val logged: StateFlow<UserDto?> = _logged.asStateFlow()

    fun onChangedUsername(value: String) {
        _username.value = value
    }

    fun onChangedPassword(value: String) {
        _password.value = value
    }

    fun toLogin() {
        viewModelScope.launch {
            val phone = _username.value
            val password = _password.value
            val request = LoginRequest(phone, password)

            tryCatchFromResponse({
                loginRepository.toLogin(request)
                val user = loginRepository.getUser().data.firstOrNull()
                _logged.value = user
            }, onFailure = { code, message ->

            })

        }
    }

}
