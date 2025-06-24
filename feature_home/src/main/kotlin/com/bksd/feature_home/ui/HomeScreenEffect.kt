package com.bksd.feature_home.ui

sealed interface HomeScreenEffect {
    data class NavigateToWordDetails(val word: String) : HomeScreenEffect
    data class NavigateToRecentSearchDetails(val queryOrId: String) : HomeScreenEffect
    data class NavigateToCategory(val categoryId: String) : HomeScreenEffect
    data class ShowMessage(val message: String) : HomeScreenEffect
}