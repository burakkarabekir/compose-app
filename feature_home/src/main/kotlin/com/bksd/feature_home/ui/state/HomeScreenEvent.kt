package com.bksd.feature_home.ui.state

sealed interface HomeScreenEvent {
    data class ItemClicked(val id: String) : HomeScreenEvent
    data object RefreshRequested : HomeScreenEvent
}