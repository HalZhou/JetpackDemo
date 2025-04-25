package com.example.jectpackdemo

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jectpackdemo.bean.dto.UserDto
import com.example.jectpackdemo.repositories.login.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    saveStateHandle: SavedStateHandle,
    private val loginRepository: LoginRepository
) : ViewModel() {

    val uiState = flow<MainUiState> {
//        val user = loginRepository.getUser().body()?.data?.firstOrNull()
////        if (user == null) {
        emit(MainUiState.NotLoggedIn)
//        } else {
//            emit(MainUiState.LoggedIn(user))
//        }
    }.catch {
        emit(MainUiState.NotLoggedIn)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), MainUiState.Loading)


    fun setUiState(uiState: MainUiState) {

    }

    fun isReady() = uiState.value != MainUiState.Loading
}

sealed class MainUiState {
    // 加载中
    data object Loading : MainUiState()

    // 未登录
    data object NotLoggedIn : MainUiState()

    // 已登录
    data class LoggedIn(val user: UserDto) : MainUiState()
}