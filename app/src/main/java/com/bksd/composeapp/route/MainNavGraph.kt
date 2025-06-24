package com.bksd.composeapp.route

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.bksd.composeapp.ui.screens.main.MainScreen
import com.bksd.route.MainGraph

fun NavGraphBuilder.mainNavGraph() {
    composable<MainGraph.MainGraphRoute> {
        MainScreen(
            animatedVisibilityScope = this
        )
    }
}