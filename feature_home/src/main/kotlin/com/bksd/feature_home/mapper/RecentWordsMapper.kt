package com.bksd.feature_home.mapper

import com.bksd.core_domain.mapper.ListMapper
import com.bksd.core_domain.model.WordDetail
import com.bksd.core_domain.result.DomainResult
import com.bksd.core_ui.UiState
import com.bksd.feature_home.ui.model.RecentWordUi
import com.bksd.feature_home.ui.state.HomeScreenState

/**
 * Mapper to convert WordOfDayModel (domain) to WordOfDayUi (UI) model.
 * Adds a default label and maintains other properties.
 */
class RecentWordsMapper : ListMapper<WordDetail, RecentWordUi> {

    override fun map(model: List<WordDetail>): List<RecentWordUi> = model.map {
        RecentWordUi(
            text = it.text,
        )
    }
}

fun DomainResult<List<WordDetail>>.toUiState(
    mapper: RecentWordsMapper
): UiState<HomeScreenState> = when (this) {
    is DomainResult.Success -> UiState.Success(
        HomeScreenState(recentSearches = mapper.map(this.data))
    )

    is DomainResult.Error -> UiState.Error(this.message)
    is DomainResult.Loading -> UiState.Loading
    is DomainResult.Empty -> UiState.Empty
}