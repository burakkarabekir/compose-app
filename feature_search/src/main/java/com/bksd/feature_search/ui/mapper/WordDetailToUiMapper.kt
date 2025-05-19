package com.bksd.feature_search.ui.mapper

import com.bksd.core_domain.mapper.BaseMapper
import com.bksd.core_domain.result.DomainResult
import com.bksd.core_ui.UiState
import com.bksd.feature_search.ui.state.SearchScreenState
import com.bksd.word_domain.model.WordDetail
import com.bksd.word_ui.model.WordDetailCardUi

/**
 * Mapper to convert WordOfDayModel (domain) to WordOfDayUi (UI) model.
 * Adds a default label and maintains other properties.
 */
class WordDetailToUiMapper : BaseMapper<WordDetail, WordDetailCardUi> {
    override fun map(model: WordDetail): WordDetailCardUi = WordDetailCardUi(
        word = model.text,
        pronunciation = model.pronunciation,
        definition = model.definition,
        synonyms = model.synonyms,
        antonyms = model.antonyms,
    )
}

fun DomainResult<WordDetail>.toSearchScreenUiState(
    mapper: WordDetailToUiMapper
): UiState<SearchScreenState> = when (this) {
    is DomainResult.Success -> UiState.Success(
        SearchScreenState(
            word = mapper.map(this.data)
        )
    )

    is DomainResult.Error -> UiState.Error(this.message)
    is DomainResult.Loading -> UiState.Loading
    is DomainResult.Empty -> UiState.Empty
}