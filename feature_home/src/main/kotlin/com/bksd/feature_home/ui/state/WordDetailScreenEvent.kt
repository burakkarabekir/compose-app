package com.bksd.feature_home.ui.state

sealed interface WordDetailScreenEvent {
    data class OnBackClicked(val id: String) : WordDetailScreenEvent
    data class OnFavoriteClicked(val id: String) : WordDetailScreenEvent
    data class OnPlayPronunciationClicked(val id: String) : WordDetailScreenEvent
}