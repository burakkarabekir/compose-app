package com.bksd.word_data.mapper

import com.bksd.core_domain.mapper.BaseMapper
import com.bksd.word_data.remote.dto.WordDto
import com.bksd.word_domain.model.WordOfDay

class WordResponseToDomainMapper : BaseMapper<WordDto, WordOfDay> {
    override fun map(
        dto: WordDto
    ): WordOfDay = WordOfDay(
        word = dto.word,
        definition = dto.results?.firstOrNull()?.definition,
        synonyms = dto.results?.firstOrNull()?.synonyms,
        antonyms = dto.results?.firstOrNull()?.antonyms,
        isFavorite = dto.isFavorite
    )
}