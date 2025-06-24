package com.bksd.composeapp.ui.screens.main

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bksd.composeapp.route.bottom_bar.AppBottomBar
import com.bksd.composeapp.route.nav_host.MainNavHost
import com.bksd.core_ui.extension.CollectFlowAsEvent
import com.bksd.route.AppNavigator
import com.bksd.route.NavigationAction
import kotlinx.coroutines.CoroutineScope
import org.koin.compose.koinInject

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    animatedVisibilityScope: AnimatedVisibilityScope,
    mainNavController: NavHostController = rememberNavController(),
    navigator: AppNavigator = koinInject(),
    coroutineScope: CoroutineScope = rememberCoroutineScope()
) {
    fun navigateBottomBarItem(route: Any) {
        mainNavController.navigate(route) {
            popUpTo(mainNavController.graph.startDestinationId) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
    CollectFlowAsEvent(navigator.mainNavigationAction) { action ->
        when (action) {
            is NavigationAction.Navigate -> navigateBottomBarItem(action.route)
            is NavigationAction.NavigateBack -> mainNavController.popBackStack()
        }
    }
    Scaffold(
        modifier = modifier.fillMaxSize(),
        bottomBar = {
            AppBottomBar(
                navController = mainNavController,
                navigateBottomBarItem = { route ->
                    navigateBottomBarItem(route)
                }
            )
        }
    ) { innerPadding ->
        MainNavHost(
            modifier = Modifier
                .padding(bottom = innerPadding.calculateBottomPadding()),
            mainNavController = mainNavController
        )
    }
}