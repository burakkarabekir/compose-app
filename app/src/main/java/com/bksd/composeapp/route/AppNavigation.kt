package com.bksd.composeapp.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.bksd.feature_home.route.homeNavGraph
import com.bksd.route.AppNavigationCommand
import com.bksd.route.AppNavigator
import com.bksd.route.AppRoutes

@Composable
fun AppNavigation(
    navigator: AppNavigator,
    navController: NavHostController = rememberNavController(),
    startDestination: String = AppRoutes.Home.route
) {
    LaunchedEffect(Unit) {
        navigator.navigation.collect { command ->
            when (command) {
                is AppNavigationCommand.Navigate -> {
                    navController.navigate(command.route) {
                        command.navOptions?.let { options ->
                            popUpTo(options.popUpToId) {
                                inclusive = options.isPopUpToInclusive()
                            }
                            launchSingleTop = options.shouldLaunchSingleTop()
                            restoreState = options.shouldRestoreState()
                        }
                    }
                }

                is AppNavigationCommand.NavigateWithArgs -> {
                    val route = buildString {
                        append(command.route)
                        command.args.forEach { (_, value) ->
                            append("/$value")
                        }
                    }
                    navController.navigate(route) {
                        command.navOptions?.let { options ->
                            popUpTo(options.popUpToId) {
                                inclusive = options.isPopUpToInclusive()
                            }
                            launchSingleTop = options.shouldLaunchSingleTop()
                            restoreState = options.shouldRestoreState()
                        }
                    }
                }

                is AppNavigationCommand.Back -> {
                    navController.popBackStack()
                }

                is AppNavigationCommand.PopUpTo -> {
                    navController.popBackStack(
                        route = command.route,
                        inclusive = command.inclusive
                    )
                }
            }
        }
    }

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        homeNavGraph(navigator)
    }
}