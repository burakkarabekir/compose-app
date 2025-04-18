package com.bksd.route

sealed class AppRoutes(val route: String) {
    data object Home : AppRoutes("home")
}