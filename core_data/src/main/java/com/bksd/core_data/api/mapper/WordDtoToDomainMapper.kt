package com.bksd.core_data.api.mapper

import com.bksd.core_data.dto.WordDto
import com.bksd.core_domain.mapper.BaseMapper
import com.bksd.core_domain.model.WordDetailModel

class WordDtoToDomainMapper : BaseMapper<WordDto, WordDetailModel> {
    override fun map(dto: WordDto): WordDetailModel = WordDetailModel(
        text = dto.word.orEmpty(),
        pronunciation = dto.pronunciation?.all,
        definition = dto.results?.firstOrNull()?.definition,
        synonyms = dto.results?.firstOrNull()?.synonyms,
        antonyms = dto.results?.firstOrNull()?.antonyms
    )
}