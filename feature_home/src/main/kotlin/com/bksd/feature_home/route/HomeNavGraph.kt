package com.bksd.feature_home.route

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.bksd.feature_home.ui.HomeScreen
import com.bksd.feature_home.ui.WordDetailScreen
import com.bksd.route.MainGraph
import com.bksd.route.WordDetailGraph

fun NavGraphBuilder.homeNavGraph() {
    composable<MainGraph.HomeScreenRoute>{
        HomeScreen()
    }
}

fun NavGraphBuilder.wordDetailGraph() {
    navigation<WordDetailGraph.WordDetailGraphRoute>(
        startDestination = WordDetailGraph.WordDetailScreenRoute()
    ) {
        composable<WordDetailGraph.WordDetailScreenRoute>{
            WordDetailScreen()
        }
    }
}