package com.bksd.feature_home.route

import com.bksd.route.AppNavigationCommand
import com.bksd.route.AppNavigator
import com.bksd.route.AppRoutes

interface HomeNavigation {
    fun navigateToHome()
}

class HomeNavigationImpl(
    private val navigator: AppNavigator
) : HomeNavigation {
    override fun navigateToHome() {
        navigator.navigate(
            AppNavigationCommand.Navigate(AppRoutes.Home.route)
        )
    }
}