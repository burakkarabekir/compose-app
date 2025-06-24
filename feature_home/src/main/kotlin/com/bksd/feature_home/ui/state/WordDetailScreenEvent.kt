package com.bksd.feature_home.ui.state

sealed interface WordDetailScreenEvent {
    data class OnFavoriteClicked(val id: String, val isFavorite: Boolean) : WordDetailScreenEvent
    data class OnPlayPronunciationClicked(val id: String) : WordDetailScreenEvent
    data object OnBackClicked : WordDetailScreenEvent
}