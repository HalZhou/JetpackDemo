package com.example.jectpackdemo.ui.home.navigation

import androidx.navigation.NavGraph
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.jectpackdemo.ui.home.HomeScreen
import kotlinx.serialization.Serializable

@Serializable
data object HomeRoute

fun NavGraphBuilder.homeRouteDestination() {
    composable<HomeRoute> {
        HomeScreen()
    }
}