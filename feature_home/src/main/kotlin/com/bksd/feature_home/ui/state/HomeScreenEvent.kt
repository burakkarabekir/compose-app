package com.bksd.feature_home.ui.state

sealed interface HomeScreenEvent {
    data class OnCategorySelected(val index: Int) : HomeScreenEvent
    data class OnRecentSearchClick(val searchQuery: String) : HomeScreenEvent
    data class OnWordOfDayClick(val wordOfDay: String) : HomeScreenEvent
}