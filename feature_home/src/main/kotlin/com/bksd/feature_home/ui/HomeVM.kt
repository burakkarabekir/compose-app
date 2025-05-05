package com.bksd.feature_home.ui

import com.bksd.core_ui.BaseVM
import com.bksd.core_ui.UiEvent.Navigate
import com.bksd.core_ui.UiEvent.NavigateCall
import com.bksd.core_ui.UiState.Loading
import com.bksd.core_ui.extension.simpleLaunch
import com.bksd.feature_home.domain.usecase.GetRecentWordsUseCase
import com.bksd.feature_home.domain.usecase.GetWordOfDayUseCase
import com.bksd.feature_home.mapper.RecentWordsMapper
import com.bksd.feature_home.mapper.WordOfDayMapper
import com.bksd.feature_home.mapper.toHomeScreenUiState
import com.bksd.feature_home.mapper.toUiState
import com.bksd.feature_home.route.HomeNavigation
import com.bksd.feature_home.ui.state.HomeScreenEvent
import com.bksd.feature_home.ui.state.HomeScreenState
import kotlinx.coroutines.flow.map

class HomeVM(
//    private val wordUseCase: GetWordUseCase,
    private val wordOfDayUseCase: GetWordOfDayUseCase,
    private val recentWordsUseCase: GetRecentWordsUseCase,
    private val homeNavigation: HomeNavigation,
    private val wordOfDayMapper: WordOfDayMapper,
    private val recentWordsMapper: RecentWordsMapper
) : BaseVM<HomeScreenState, HomeScreenEvent>(initialState = Loading) {

    init {
        fetchWordOfDay()
        fetchRecentWords()
    }

    private fun fetchWordOfDay() = simpleLaunch {
        wordOfDayUseCase.invoke(Unit)
            .map { result -> result.toHomeScreenUiState(wordOfDayMapper) }
            .collect { state -> updateState { state } }
    }

    private fun fetchRecentWords() = simpleLaunch {
        recentWordsUseCase.invoke(Unit)
            .map { result -> result.toUiState(recentWordsMapper) }
            .collect { state -> updateState { state } }
    }

    override fun onEvent(event: HomeScreenEvent) {
        when (event) {
            is HomeScreenEvent.ItemClicked -> sendEvent(Navigate(event.id))
            is HomeScreenEvent.OnCategorySelected -> sendEvent(Navigate(event.index.toString()))
            is HomeScreenEvent.OnRecentSearchClick -> sendEvent(Navigate(event.query))
            is HomeScreenEvent.OnSearch -> sendEvent(
                NavigateCall {
                    homeNavigation.navigateToSearch()
                })

            is HomeScreenEvent.OnWordOfDayClick -> sendEvent(
                NavigateCall {
                homeNavigation.navigateToWordOfDayDetail(
                    uiState.value.getOrNull()?.wordOfDay!!
                )
            })
        }
    }
}