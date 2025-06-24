package com.bksd.route

import androidx.navigation.NavOptionsBuilder
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

class AppNavigatorImpl: AppNavigator {

    private val _appNavigationAction = Channel<NavigationAction>(Channel.BUFFERED)
    override val appNavigationAction = _appNavigationAction.receiveAsFlow()

    private val _mainNavigationAction = Channel<NavigationAction>(Channel.BUFFERED)
    override val mainNavigationAction = _mainNavigationAction.receiveAsFlow()

    override suspend fun navigate( route: Any,
                                   navOptions: NavOptionsBuilder.() -> Unit) {
        val navigationChannel = getCorrectChannelForRoute(route)
        navigationChannel.send(
          NavigationAction.Navigate(
                route = route,
                navOptions = navOptions
            )
        )
    }

    override suspend fun navigateBack(
        route: Any?,
        inclusive: Boolean,
        saveState: Boolean
    ) {
        _appNavigationAction.send(
           NavigationAction.NavigateBack(route, inclusive, saveState)
        )
    }

    override suspend fun navigateRoot(
        route: Any,
        navOptions: NavOptionsBuilder.() -> Unit
    ) {
        navigateBack(
            route = MainGraph.MainGraphRoute,
            inclusive = false
        )
        navigate(route, navOptions)
    }

    private fun getCorrectChannelForRoute(route: Any): Channel<NavigationAction> = when (route) {
        is MainGraph.HomeScreenRoute,
        is MainGraph.SearchScreenRoute -> _mainNavigationAction
        else -> _appNavigationAction
    }
}