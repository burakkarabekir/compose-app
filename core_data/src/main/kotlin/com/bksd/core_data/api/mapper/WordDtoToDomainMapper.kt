package com.bksd.core_data.api.mapper

import com.bksd.core_data.remote.dto.WordDto
import com.bksd.core_domain.mapper.BaseMapper
import com.bksd.core_domain.model.WordDetail

class WordDtoToDomainMapper : BaseMapper<WordDto, WordDetail> {
    override fun map(dto: WordDto): WordDetail = WordDetail(
        text = dto.word.orEmpty(),
        pronunciation = dto.pronunciation?.all,
        definition = dto.results?.firstOrNull()?.definition,
        synonyms = dto.results?.firstOrNull()?.synonyms,
        antonyms = dto.results?.firstOrNull()?.antonyms
    )
}