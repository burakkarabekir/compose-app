package com.bksd.route

import androidx.navigation.NavType
import androidx.navigation.navArgument
import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

sealed class AppRoutes(val route: String) {
    data object Home : AppRoutes("home")
    data object WordDetail : AppRoutes("word_detail/{word}/{pronunciation}") {
        fun withArgs(
            word: String,
            pronunciation: String
        ): String = "word_detail/${word.encode()}/${pronunciation.encode()}"

        val arguments = listOf(
            navArgument("word") { type = NavType.StringType },
            navArgument("pronunciation") { type = NavType.StringType },
        )
    }

    companion object {
        // Optional: Utility to encode strings for safe URL usage
        private fun String.encode(): String =
            URLEncoder.encode(this, StandardCharsets.UTF_8.toString())

        fun String.decode(): String =
            URLDecoder.decode(this, StandardCharsets.UTF_8.toString())
    }
}