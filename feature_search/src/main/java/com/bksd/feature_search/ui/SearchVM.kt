package com.bksd.feature_search.ui

import com.bksd.core_ui.BaseVM
import com.bksd.core_ui.UiState.Loading
import com.bksd.feature_search.ui.event.SearchScreenEvent
import com.bksd.feature_search.ui.state.SearchScreenState

class SearchVM() : BaseVM<SearchScreenState, SearchScreenEvent>(initialState = Loading) {
    override fun onEvent(event: SearchScreenEvent) {
        when (event) {
            is SearchScreenEvent.OnSearch -> Unit
            is SearchScreenEvent.OnSearchQueryChange -> Unit
        }
    }
}