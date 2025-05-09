package com.bksd.feature_search.ui

import com.bksd.core_ui.BaseVM
import com.bksd.core_ui.UiState
import com.bksd.core_ui.extension.simpleLaunch
import com.bksd.feature_search.domain.usecase.GetWordUseCase
import com.bksd.feature_search.ui.event.SearchScreenEvent
import com.bksd.feature_search.ui.mapper.WordDetailToUiMapper
import com.bksd.feature_search.ui.mapper.toSearchScreenUiState
import com.bksd.feature_search.ui.state.SearchScreenState
import kotlinx.coroutines.flow.map

class SearchVM(
    private val wordUseCase: GetWordUseCase,
    private val mapper: WordDetailToUiMapper,
) : BaseVM<SearchScreenState, SearchScreenEvent>(initialState = UiState.Initial) {

    private fun fetchWord(word: String) = simpleLaunch {
        wordUseCase.invoke(GetWordUseCase.Params(word))
            .map { result -> result.toSearchScreenUiState(mapper) }
            .collect { state -> updateState { state } }
    }
    override fun onEvent(event: SearchScreenEvent) {
        when (event) {
            is SearchScreenEvent.OnSearch -> fetchWord(event.query)
            is SearchScreenEvent.OnSearchQueryChange -> Unit
        }
    }
}