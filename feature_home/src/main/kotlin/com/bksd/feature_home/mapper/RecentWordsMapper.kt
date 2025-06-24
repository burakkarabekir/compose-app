package com.bksd.feature_home.mapper

import com.bksd.core_domain.mapper.ListMapper
import com.bksd.core_domain.result.DomainResult
import com.bksd.core_ui.UiState
import com.bksd.feature_home.ui.model.RecentWordUi
import com.bksd.feature_home.ui.state.HomeScreenState
import com.bksd.feature_home.ui.state.HomeScreenUi
import com.bksd.word_domain.model.WordDetail
import kotlinx.coroutines.flow.MutableStateFlow

/**
 * Mapper to convert WordOfDayModel (domain) to WordOfDayUi (UI) model.
 * Adds a default label and maintains other properties.
 */
class RecentWordsMapper : ListMapper<WordDetail, RecentWordUi> {
    override fun map(from: List<WordDetail>): List<RecentWordUi> = from.map {
        RecentWordUi(
            text = it.text,

        )
    }
}

fun DomainResult<List<WordDetail>>.toHomeScreenUiState(
    state: MutableStateFlow<UiState<HomeScreenState>>,
    mapper: RecentWordsMapper
): UiState<HomeScreenState> = when (this) {
    is DomainResult.Success -> {
        val recentWordsUi = mapper.map(this.data)
        val currentState = (state.value as? UiState.Success)?.data
        UiState.Success(
            currentState?.copy(
                uiModel = currentState.uiModel.copy(
                    recentSearches = recentWordsUi
                )
            ) ?: HomeScreenState(
                uiModel = HomeScreenUi(recentSearches = recentWordsUi)
            )
        )
    }

    is DomainResult.Error -> UiState.Error(this.message)
    is DomainResult.Loading -> UiState.Loading
    is DomainResult.Empty -> UiState.Empty
}