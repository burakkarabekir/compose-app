package com.bksd.core_data.api.mapper

import com.bksd.core_data.remote.dto.WordDto
import com.bksd.core_domain.mapper.BaseMapper
import com.bksd.core_domain.model.WordOfDay

class WordResponseToDomainMapper : BaseMapper<WordDto, WordOfDay> {
    override fun map(
        dto: WordDto
    ): WordOfDay = WordOfDay(
        word = dto.word,
        definition = dto.results?.firstOrNull()?.definition,
        synonyms = dto.results?.firstOrNull()?.synonyms,
        antonyms = dto.results?.firstOrNull()?.antonyms,
        pronunciation = dto.pronunciation?.all
    )
}