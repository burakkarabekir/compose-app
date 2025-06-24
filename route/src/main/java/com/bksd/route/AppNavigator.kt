package com.bksd.route

import androidx.navigation.NavOptionsBuilder
import kotlinx.coroutines.flow.Flow

interface AppNavigator {
    val appNavigationAction: Flow<NavigationAction>
    val mainNavigationAction: Flow<NavigationAction>
    suspend fun navigate(
        route: Any,
        navOptions: NavOptionsBuilder.() -> Unit = {}
    )

    suspend fun navigateBack(
        route: Any? = null,
        inclusive: Boolean = true,
        saveState: Boolean = false
    )
    suspend fun navigateRoot(
        route: Any,
        navOptions: NavOptionsBuilder.() -> Unit = {}
    )
}