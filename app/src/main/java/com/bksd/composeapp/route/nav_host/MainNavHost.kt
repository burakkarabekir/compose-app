package com.bksd.composeapp.route.nav_host

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.bksd.feature_home.route.homeNavGraph
import com.bksd.feature_search.route.searchNavGraph
import com.bksd.route.MainGraph

@Composable
fun MainNavHost(
    modifier: Modifier = Modifier,
    mainNavController: NavHostController
) {
    NavHost(
        modifier = modifier,
        navController = mainNavController,
        startDestination = MainGraph.HomeScreenRoute,
        enterTransition = {
            EnterTransition.None
        },
        exitTransition = {
            ExitTransition.None
        },
        popEnterTransition = {
            EnterTransition.None
        },
        popExitTransition = {
            ExitTransition.None
        }
    ) {
        homeNavGraph()
        searchNavGraph()
    }
}