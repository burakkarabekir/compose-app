package com.bksd.core_data.api.mapper

import com.bksd.core_data.local.entity.WordEntity
import com.bksd.core_data.remote.dto.WordDto
import com.bksd.core_domain.mapper.BaseMapper

class WordDtoToEntityMapper : BaseMapper<WordDto, WordEntity> {
    override fun map(dto: WordDto): WordEntity = WordEntity(
        word = dto.word.orEmpty(),
        definition = dto.results?.firstOrNull()?.definition.orEmpty(),
        pronunciation = dto.pronunciation?.all.orEmpty(),
        timestamp = System.currentTimeMillis()
    )
}