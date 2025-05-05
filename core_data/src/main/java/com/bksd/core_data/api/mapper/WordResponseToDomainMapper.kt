package com.bksd.core_data.api.mapper

import com.bksd.core_data.dto.WordOfDayResponse
import com.bksd.core_domain.mapper.BaseMapper
import com.bksd.core_domain.model.WordOfDayModel

class WordResponseToDomainMapper : BaseMapper<WordOfDayResponse, WordOfDayModel> {
    override fun map(from: WordOfDayResponse): WordOfDayModel {
        return WordOfDayModel(
            word = from.word,
            definition = from.results?.firstOrNull()?.definition,
            synonyms = from.results?.firstOrNull()?.synonyms,
            antonyms = from.results?.firstOrNull()?.antonyms,
            pronunciation = from.pronunciation?.all
        )
    }
}