package com.bksd.feature_home.route

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.bksd.feature_home.ui.HomeScreen
import com.bksd.route.AppNavigator
import com.bksd.route.AppRoutes

fun NavGraphBuilder.homeNavGraph(navigator: AppNavigator) {
    composable(
        route = AppRoutes.Home.route
    ) {
        HomeScreen(navigator = navigator)
    }
}