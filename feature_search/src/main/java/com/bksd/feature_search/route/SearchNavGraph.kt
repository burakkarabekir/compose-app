package com.bksd.feature_search.route

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.bksd.feature_search.ui.SearchScreen
import com.bksd.route.AppNavigator
import com.bksd.route.AppRoutes

fun NavGraphBuilder.searchNavGraph(navigator: AppNavigator) {
    composable(
        route = AppRoutes.Search.route,
    ) { backStackEntry ->
        SearchScreen(
            navigator = navigator,
            onBackClick = { navigator.navigateBack() },
        )
    }
}

