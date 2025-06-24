package com.bksd.route

import androidx.navigation.NavOptionsBuilder

sealed interface NavigationAction {
    data class Navigate(
        val route: Any,
        val navOptions: NavOptionsBuilder.() -> Unit = {}
    ) : NavigationAction

    data class NavigateBack(
        val route: Any? = null,
        val inclusive: Boolean = true,
        val saveState: Boolean = false
    ) : NavigationAction
}