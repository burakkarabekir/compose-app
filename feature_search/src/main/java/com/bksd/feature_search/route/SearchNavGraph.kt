package com.bksd.feature_search.route

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.bksd.feature_search.ui.SearchScreen
import com.bksd.route.MainGraph

fun NavGraphBuilder.searchNavGraph() {
    composable<MainGraph.SearchScreenRoute>{
        SearchScreen()
    }
}

