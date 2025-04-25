package com.example.jectpackdemo.ui.login

import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.jectpackdemo.bean.dto.UserDto
import kotlinx.serialization.Serializable

@Serializable
data object LoginRoute


fun NavGraphBuilder.loginRouteDestination(onLoggedIn : (UserDto) -> Unit) {
    composable<LoginRoute> {
        LoginScreen(onLoggedIn = onLoggedIn)
    }
}


