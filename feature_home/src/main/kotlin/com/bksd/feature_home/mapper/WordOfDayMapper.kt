package com.bksd.feature_home.mapper

import com.bksd.core_domain.mapper.BaseMapper
import com.bksd.core_domain.model.WordOfDayModel
import com.bksd.core_domain.result.DomainResult
import com.bksd.core_ui.UiState
import com.bksd.feature_home.ui.model.WordOfDayUi
import com.bksd.feature_home.ui.state.HomeScreenState

/**
 * Mapper to convert WordOfDayModel (domain) to WordOfDayUi (UI) model.
 * Adds a default label and maintains other properties.
 */
class WordOfDayMapper : BaseMapper<WordOfDayModel, WordOfDayUi> {

    companion object {
        private const val DEFAULT_LABEL = "Word of the Day"
    }

    override fun map(input: WordOfDayModel): WordOfDayUi = WordOfDayUi(
        label = DEFAULT_LABEL,
        word = input.word.orEmpty(),
        pronunciation = input.pronunciation.orEmpty(),
        definition = input.definition.orEmpty()
    )
}

fun DomainResult<WordOfDayModel>.toHomeScreenUiState(
    mapper: WordOfDayMapper
): UiState<HomeScreenState> {
    return when (this) {
        is DomainResult.Success -> UiState.Success(
            HomeScreenState(wordOfDay = mapper.map(this.data))
        )

        is DomainResult.Error -> UiState.Error(this.message)
        is DomainResult.Loading -> UiState.Loading
        is DomainResult.Empty -> UiState.Empty
    }
}