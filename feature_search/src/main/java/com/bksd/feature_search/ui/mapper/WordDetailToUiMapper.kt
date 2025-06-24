package com.bksd.feature_search.ui.mapper

import com.bksd.core_domain.mapper.BaseMapper
import com.bksd.word_domain.model.WordDetail
import com.bksd.word_ui.model.WordDetailCardUi

/**
 * Mapper to convert WordOfDayModel (domain) to WordOfDayUi (UI) model.
 * Adds a default label and maintains other properties.
 */
class WordDetailToUiMapper : BaseMapper<WordDetail, WordDetailCardUi> {
    override fun map(from: WordDetail): WordDetailCardUi = WordDetailCardUi(
        word = from.text,
        definition = from.definition,
        synonyms = from.synonyms,
        antonyms = from.antonyms,
    )
}