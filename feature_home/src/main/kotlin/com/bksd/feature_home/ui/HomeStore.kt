package com.bksd.feature_home.ui

import androidx.lifecycle.viewModelScope
import com.bksd.core_domain.result.DomainResult
import com.bksd.core_ui.BaseStore
import com.bksd.core_ui.extension.simpleLaunch
import com.bksd.feature_home.domain.usecase.GetRecentWordsUseCase
import com.bksd.feature_home.domain.usecase.GetWordOfDayUseCase
import com.bksd.feature_home.mapper.RecentWordsMapper
import com.bksd.feature_home.mapper.WordOfDayMapper
import com.bksd.feature_home.route.HomeNavigation
import com.bksd.feature_home.ui.state.HomeScreenEvent
import com.bksd.feature_home.ui.state.HomeScreenState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeStore(
    private val wordOfDayUseCase: GetWordOfDayUseCase,
    private val recentWordsUseCase: GetRecentWordsUseCase,
    private val homeNavigation: HomeNavigation,
    private val wordOfDayMapper: WordOfDayMapper,
    private val recentWordsMapper: RecentWordsMapper
) : BaseStore<HomeScreenState, HomeScreenEvent, HomeScreenEffect>() {

    private val _uiState = MutableStateFlow(HomeScreenState())
    override val uiState = _uiState.asStateFlow()

    private val _uiEffect = Channel<HomeScreenEffect>(Channel.BUFFERED)
    override val uiEffect = _uiEffect.receiveAsFlow()

    init {
        fetchWordOfDay()
        fetchRecentWords()
    }

    private fun fetchWordOfDay() = simpleLaunch {
        wordOfDayUseCase.invoke(Unit)
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
                            currentState.uiModel.copy(wordOfDay = wordOfDayMapper.map(state.data))
                                .let { currentState.copy(uiModel = it) }
                        }
                    }
                }
            }
    }

    private fun fetchRecentWords() = simpleLaunch {
        recentWordsUseCase.invoke(Unit)
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
                            currentState.uiModel.copy(recentSearches = recentWordsMapper.map(state.data))
                                .let { currentState.copy(uiModel = it) }
                        }
                    }
                }
            }
    }


    override fun onEvent(event: HomeScreenEvent) {
        viewModelScope.launch {
        when (event) {
            is HomeScreenEvent.OnCategorySelected -> {
                _uiEffect.send(HomeScreenEffect.NavigateToCategory(event.index.toString()))
            }

            is HomeScreenEvent.OnRecentSearchClick -> {
                _uiEffect.send(HomeScreenEffect.NavigateToRecentSearchDetails(event.searchQuery))
            }

            is HomeScreenEvent.OnWordOfDayClick -> {
                _uiEffect.send(HomeScreenEffect.NavigateToWordDetails(event.wordOfDay))
            }
        }
        }
    }
}