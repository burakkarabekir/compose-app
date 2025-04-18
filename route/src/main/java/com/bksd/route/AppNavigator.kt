package com.bksd.route

import androidx.navigation.NavOptions
import kotlinx.coroutines.flow.Flow

interface AppNavigator {
    val navigation: Flow<AppNavigationCommand>
    fun navigate(command: AppNavigationCommand)
    fun navigateBack()
    fun popUpTo(route: String, inclusive: Boolean = false)
    fun navigateAndClearBackStack(route: String)
}

sealed interface AppNavigationCommand {
    data class Navigate(
        val route: String,
        val navOptions: NavOptions? = null
    ) : AppNavigationCommand

    data class NavigateWithArgs(
        val route: String,
        val args: Map<String, String>,
        val navOptions: NavOptions? = null
    ) : AppNavigationCommand

    data class PopUpTo(
        val route: String,
        val inclusive: Boolean
    ) : AppNavigationCommand

    data object Back : AppNavigationCommand
}