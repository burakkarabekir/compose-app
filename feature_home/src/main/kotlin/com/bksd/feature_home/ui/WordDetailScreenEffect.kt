package com.bksd.feature_home.ui

sealed interface WordDetailScreenEffect {
    // data class SetFavoriteStatus(val id: String, val isFavorite: Boolean) : WordDetailScreenEffect
    data class PlayAudio(val id: String) : WordDetailScreenEffect
    data object NavigateBack : WordDetailScreenEffect
}