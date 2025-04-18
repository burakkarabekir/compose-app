package com.bksd.route

import androidx.navigation.NavOptions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class AppNavigatorImpl(
    private val scope: CoroutineScope = CoroutineScope(
        context = SupervisorJob() + Dispatchers.Main.immediate
    )
) : AppNavigator {
    private val _navigation = Channel<AppNavigationCommand>()
    override val navigation = _navigation.receiveAsFlow()

    override fun navigate(command: AppNavigationCommand) {
        scope.launch { _navigation.send(command) }
    }

    override fun navigateBack() {
        scope.launch { _navigation.send(AppNavigationCommand.Back) }
    }

    override fun popUpTo(route: String, inclusive: Boolean) {
        scope.launch { _navigation.send(AppNavigationCommand.PopUpTo(route, inclusive)) }
    }

    override fun navigateAndClearBackStack(route: String) {
        scope.launch {
            _navigation.send(
                AppNavigationCommand.Navigate(
                    route = route,
                    navOptions = NavOptions.Builder()
                        .setPopUpTo(0, true)
                        .setLaunchSingleTop(true)
                        .build()
                )
            )
        }
    }
}