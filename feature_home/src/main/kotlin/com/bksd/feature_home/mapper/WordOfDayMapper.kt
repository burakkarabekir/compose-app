package com.bksd.feature_home.mapper

import com.bksd.core_domain.mapper.BaseMapper
import com.bksd.core_domain.result.DomainResult
import com.bksd.core_ui.UiState
import com.bksd.feature_home.ui.state.HomeScreenState
import com.bksd.feature_home.ui.state.HomeScreenUi
import com.bksd.word_domain.model.WordOfDay
import com.bksd.word_ui.model.WordDetailUi
import com.bksd.word_ui.model.WordOfDayDetailCardUi
import kotlinx.coroutines.flow.MutableStateFlow

/**
 * Mapper to convert WordOfDayModel (domain) to WordOfDayUi (UI) model.
 * Adds a default label and maintains other properties.
 */
class WordOfDayMapper : BaseMapper<WordOfDay, WordDetailUi> {

    companion object {
        private const val DEFAULT_LABEL = "Word of the Day"
    }

    override fun map(from: WordOfDay): WordDetailUi = WordOfDayDetailCardUi(
        label = DEFAULT_LABEL,
        word = from.word.orEmpty(),
        definition = from.definition.orEmpty(),
        synonyms = from.synonyms,
        antonyms = from.antonyms,
        isFavorite = from.isFavorite
    )
}

fun DomainResult<WordOfDay>.toHomeScreenUiState(
    state: MutableStateFlow<UiState<HomeScreenState>>,
    mapper: WordOfDayMapper
): UiState<HomeScreenState> {
    return when (this) {
        is DomainResult.Success -> {
            val wordOfDayUi = mapper.map(this.data)
            val currentState = (state.value as? UiState.Success)?.data
            UiState.Success(
                currentState?.copy(
                    uiModel = currentState.uiModel.copy(
                        wordOfDay = wordOfDayUi
                    )
                ) ?: HomeScreenState(
                    uiModel = HomeScreenUi(wordOfDay = wordOfDayUi)
                )
            )
        }

        is DomainResult.Error -> UiState.Error(this.message)
        is DomainResult.Loading -> UiState.Loading
        is DomainResult.Empty -> UiState.Empty
    }
}