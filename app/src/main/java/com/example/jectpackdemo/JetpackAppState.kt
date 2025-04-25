package com.example.jectpackdemo

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.jectpackdemo.repositories.login.LoginRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

@Composable
fun rememberJetpackAppState(
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController(),
    loginRepository: LoginRepository
) = remember {
    JetpackAppState(navController, coroutineScope, loginRepository = loginRepository)
}

@Stable
class JetpackAppState(
    val navController: NavHostController,
    coroutineScope: CoroutineScope,
    val loginRepository: LoginRepository
) {

    val logged = flow {
        emit(loginRepository.getUser())
    }.map {
        val data = it.data.firstOrNull()
        data != null
    }
        .catch {
            emit(false)
        }
        .stateIn(coroutineScope, started = SharingStarted.WhileSubscribed(5000L), false)



}