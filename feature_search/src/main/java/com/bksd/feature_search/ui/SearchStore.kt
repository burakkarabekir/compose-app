package com.bksd.feature_search.ui

import androidx.lifecycle.viewModelScope
import com.bksd.core_domain.result.DomainResult
import com.bksd.core_ui.BaseStore
import com.bksd.core_ui.extension.simpleLaunch
import com.bksd.feature_search.domain.usecase.GetWordUseCase
import com.bksd.feature_search.ui.SearchScreenEffect.AddToFavorite
import com.bksd.feature_search.ui.SearchScreenEffect.NavigateToWordDetail
import com.bksd.feature_search.ui.event.SearchScreenEvent
import com.bksd.feature_search.ui.event.SearchScreenEvent.OnFavoriteClick
import com.bksd.feature_search.ui.event.SearchScreenEvent.OnSearch
import com.bksd.feature_search.ui.event.SearchScreenEvent.OnSearchQueryChange
import com.bksd.feature_search.ui.event.SearchScreenEvent.OnWordClick
import com.bksd.feature_search.ui.mapper.WordDetailToUiMapper
import com.bksd.feature_search.ui.state.SearchScreenState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchStore(
    private val wordUseCase: GetWordUseCase,
    private val mapper: WordDetailToUiMapper,
) : BaseStore<SearchScreenState, SearchScreenEvent, SearchScreenEffect>() {

    private val _uiState = MutableStateFlow(SearchScreenState())
    override val uiState = _uiState.asStateFlow()

    private val _uiEffect = Channel<SearchScreenEffect>(Channel.BUFFERED)
    override val uiEffect = _uiEffect.receiveAsFlow()

    private fun fetchWord(word: String) = simpleLaunch {
        wordUseCase.invoke(GetWordUseCase.Params(word))
            .collect { state ->
                _uiState.update { currentState ->
                    currentState.copy(
                        isLoading = state is DomainResult.Loading
                    )
                }
                when (state) {
                    DomainResult.Empty -> Unit
                    is DomainResult.Error -> Unit
                    DomainResult.Loading -> {}
                    is DomainResult.Success -> {
                        _uiState.update { currentState ->
                            currentState.uiModel.copy( mapper.map(state.data))
                                .let { currentState.copy(uiModel = it) }
                        }
                    }
                }
            }
    }

    override fun onEvent(event: SearchScreenEvent) {
        viewModelScope.launch {
            when (event) {
                is OnSearch -> fetchWord(event.query)
                is OnSearchQueryChange -> Unit
                is OnWordClick -> _uiEffect.send(NavigateToWordDetail(event.query))
                is OnFavoriteClick -> _uiEffect.send(AddToFavorite(event.query))
            }
        }
    }
}