package com.bksd.route

import kotlinx.serialization.Serializable

@Serializable
object MainGraph {
    @Serializable
    data object MainGraphRoute

    @Serializable
    data object HomeScreenRoute

    @Serializable
    data object SearchScreenRoute
}

@Serializable
object WordDetailGraph {
    @Serializable
    data object WordDetailGraphRoute

    @Serializable
    data class WordDetailScreenRoute(val word: String = "")
}