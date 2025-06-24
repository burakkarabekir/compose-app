package com.bksd.feature_search.ui.event

sealed interface SearchScreenEvent {
    data class OnSearchQueryChange(val query: String) : SearchScreenEvent
    data class OnSearch(val query: String) : SearchScreenEvent
    data class OnWordClick(val query: String) : SearchScreenEvent
    data class OnFavoriteClick(val query: String) : SearchScreenEvent
}