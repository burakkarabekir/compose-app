package com.bksd.feature_home.mapper

import com.bksd.core_domain.mapper.BaseMapper
import com.bksd.word_domain.model.WordDetail
import com.bksd.word_ui.model.WordDetailCardUi
import com.bksd.word_ui.model.WordDetailUi

/**
 * Mapper to convert WordOfDayModel (domain) to WordOfDayUi (UI) model.
 * Adds a default label and maintains other properties.
 */
class WordDetailMapper : BaseMapper<WordDetail, WordDetailUi> {

    override fun map(from: WordDetail): WordDetailUi = WordDetailCardUi(
        word = from.text,
        definition = from.definition,
        synonyms = from.synonyms,
        antonyms = from.antonyms,
        isFavorite = from.isFavorite
    )
}