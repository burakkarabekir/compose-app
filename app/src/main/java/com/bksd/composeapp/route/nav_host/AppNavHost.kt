package com.bksd.composeapp.route.nav_host

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.bksd.composeapp.route.mainNavGraph
import com.bksd.core_ui.extension.CollectFlowAsEvent
import com.bksd.feature_home.route.wordDetailGraph
import com.bksd.route.AppNavigator
import com.bksd.route.NavigationAction
import org.koin.compose.koinInject

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    startDestination: Any,
    appNavController: NavHostController,
    navigator: AppNavigator = koinInject(),
) {
    CollectFlowAsEvent(navigator.appNavigationAction) { action ->
        when (action) {
            is NavigationAction.Navigate -> {
                appNavController.navigate(action.route) {
                    launchSingleTop = true
                    action.navOptions(this)
                }
            }

            is NavigationAction.NavigateBack -> {
                action.route?.let { route ->
                    appNavController.popBackStack(
                        route = route,
                        inclusive = action.inclusive,
                        saveState = action.saveState
                    )
                } ?: appNavController.navigateUp()
            }
        }
    }
    NavHost(
        modifier = modifier,
        navController = appNavController,
        startDestination = startDestination,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
        popEnterTransition = { EnterTransition.None },
        popExitTransition = { ExitTransition.None }
    ) {
        wordDetailGraph()
        mainNavGraph()
    }
}