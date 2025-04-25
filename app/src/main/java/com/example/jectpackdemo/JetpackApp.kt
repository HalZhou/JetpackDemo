package com.example.jectpackdemo

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.adaptive.WindowAdaptiveInfo
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.jectpackdemo.bean.dto.UserDto
import com.example.jectpackdemo.ui.home.navigation.HomeRoute
import com.example.jectpackdemo.ui.home.navigation.homeRouteDestination
import com.example.jectpackdemo.ui.login.LoginRoute
import com.example.jectpackdemo.ui.login.LoginScreen
import com.example.jectpackdemo.ui.login.loginRouteDestination

@Composable
fun JetpackApp(
    appState: JetpackAppState,
    mainViewModel: MainViewModel,
    windowAdaptiveInfo: WindowAdaptiveInfo = currentWindowAdaptiveInfo(),
) {
    val uiState by mainViewModel.uiState.collectAsStateWithLifecycle()

    val modifier = Modifier
        .fillMaxSize()

    val navController: NavHostController = rememberNavController()
    when (uiState) {
        is MainUiState.Loading -> {

        }

        is MainUiState.LoggedIn -> {
            JetpackAppNavHost(navController, modifier = modifier) {
                mainViewModel.setUiState(MainUiState.NotLoggedIn)
            }
        }

        is MainUiState.NotLoggedIn -> {
            JetpackLoginNavHost(navController, modifier) {
                mainViewModel.setUiState(MainUiState.LoggedIn(it))
            }
        }
    }
}

@Composable
fun JetpackLoginNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    onLoggedIn: (UserDto) -> Unit
) {
    NavHost(navController, modifier = modifier, startDestination = LoginRoute) {
        loginRouteDestination(onLoggedIn)
    }
}

@Composable
fun JetpackAppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    onLogout: () -> Unit
) {
    Scaffold(
        bottomBar = {

        },
        topBar = {

        }
    ) { innerPadding ->
        NavHost(
            navController,
            modifier = modifier.padding(innerPadding),
            startDestination = HomeRoute
        ) {
            homeRouteDestination()
        }
    }
}