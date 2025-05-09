package com.bksd.core_data.api.mapper

import com.bksd.core_data.dto.WordDto
import com.bksd.core_domain.mapper.BaseMapper
import com.bksd.core_domain.model.WordOfDayModel

class WordResponseToDomainMapper : BaseMapper<WordDto, WordOfDayModel> {
    override fun map(
        dto: WordDto
    ): WordOfDayModel = WordOfDayModel(
        word = dto.word,
        definition = dto.results?.firstOrNull()?.definition,
        synonyms = dto.results?.firstOrNull()?.synonyms,
        antonyms = dto.results?.firstOrNull()?.antonyms,
        pronunciation = dto.pronunciation?.all
    )
}