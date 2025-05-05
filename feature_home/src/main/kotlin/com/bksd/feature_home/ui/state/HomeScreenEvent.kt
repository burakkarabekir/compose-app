package com.bksd.feature_home.ui.state

/*for UI/user-driven actions (e.g. clicks, inputs).*/
sealed interface HomeScreenEvent {
    data class ItemClicked(val id: String) : HomeScreenEvent
    data object OnSearch : HomeScreenEvent
    data class OnCategorySelected(val index: Int) : HomeScreenEvent
    data class OnRecentSearchClick(val query: String) : HomeScreenEvent
    data object OnWordOfDayClick : HomeScreenEvent
}