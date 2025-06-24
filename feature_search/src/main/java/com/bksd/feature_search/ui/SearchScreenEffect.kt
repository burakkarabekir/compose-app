package com.bksd.feature_search.ui

sealed interface SearchScreenEffect {
    data class AddToFavorite(val itemId: String) : SearchScreenEffect
    data class NavigateToWordDetail(val itemId: String) : SearchScreenEffect
    data class ShowMessage(val message: String) : SearchScreenEffect
}