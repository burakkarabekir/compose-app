package com.bksd.feature_home.route

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.bksd.feature_home.ui.HomeScreen
import com.bksd.feature_home.ui.WordDetailScreen
import com.bksd.route.AppNavigator
import com.bksd.route.AppRoutes
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

fun NavGraphBuilder.homeNavGraph(navigator: AppNavigator) {
    composable(
        route = AppRoutes.Home.route
    ) {
        HomeScreen(navigator = navigator)
    }
}

fun NavGraphBuilder.wordDetailNavGraph(navigator: AppNavigator) {
    composable(
        route = AppRoutes.WordDetail.route,
        arguments = AppRoutes.WordDetail.arguments
    ) { backStackEntry ->
        val word = backStackEntry.arguments?.getString("word")?.decode().orEmpty()
        val pronunciation = backStackEntry.arguments?.getString("pronunciation")?.decode().orEmpty()

        WordDetailScreen(
            word = word,
            pronunciation = pronunciation,
            definition = "definition",
            synonyms = listOf("synonyms"),
            antonyms = listOf("antonyms"),
            isFavorite = false,
            onBackClick = { navigator.navigateBack() },
            onFavoriteClick = { },
            onPlayAudioClick = { }
        )
    }
}

private fun String.decode(): String =
    URLDecoder.decode(this, StandardCharsets.UTF_8.toString())