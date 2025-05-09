package com.bksd.feature_search.ui.mapper

import com.bksd.core_domain.mapper.BaseMapper
import com.bksd.core_domain.model.WordDetailModel
import com.bksd.core_domain.result.DomainResult
import com.bksd.core_ui.UiState
import com.bksd.core_ui.model.WordDetailCardUi
import com.bksd.feature_search.ui.state.SearchScreenState

/**
 * Mapper to convert WordOfDayModel (domain) to WordOfDayUi (UI) model.
 * Adds a default label and maintains other properties.
 */
class WordDetailModelToUiMapper : BaseMapper<WordDetailModel, WordDetailCardUi> {
    override fun map(model: WordDetailModel): WordDetailCardUi = WordDetailCardUi(
        word = model.text,
        pronunciation = model.pronunciation,
        definition = model.definition,
        synonyms = model.synonyms,
        antonyms = model.antonyms,
    )
}

fun DomainResult<WordDetailModel>.toSearchScreenUiState(
    mapper: WordDetailModelToUiMapper
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