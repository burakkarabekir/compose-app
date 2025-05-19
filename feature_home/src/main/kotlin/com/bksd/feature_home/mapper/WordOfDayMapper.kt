package com.bksd.feature_home.mapper

import com.bksd.core_domain.mapper.BaseMapper
import com.bksd.core_domain.model.WordOfDay
import com.bksd.core_domain.result.DomainResult
import com.bksd.core_ui.UiState
import com.bksd.core_ui.model.WordDetailUi
import com.bksd.core_ui.model.WordOfDayDetailCardUi
import com.bksd.feature_home.ui.state.HomeScreenState

/**
 * Mapper to convert WordOfDayModel (domain) to WordOfDayUi (UI) model.
 * Adds a default label and maintains other properties.
 */
class WordOfDayMapper : BaseMapper<WordOfDay, WordDetailUi> {

    companion object {
        private const val DEFAULT_LABEL = "Word of the Day"
    }

    override fun map(model: WordOfDay): WordDetailUi = WordOfDayDetailCardUi(
        label = DEFAULT_LABEL,
        word = model.word.orEmpty(),
        pronunciation = model.pronunciation.orEmpty(),
        definition = model.definition.orEmpty(),
        synonyms = model.synonyms,
        antonyms = model.antonyms
    )
}

fun DomainResult<WordOfDay>.toHomeScreenUiState(
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