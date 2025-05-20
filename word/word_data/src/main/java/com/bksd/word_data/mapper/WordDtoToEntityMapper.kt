package com.bksd.word_data.mapper

import com.bksd.core_domain.mapper.BaseMapper
import com.bksd.word_data.local.entity.WordEntity
import com.bksd.word_data.remote.dto.WordDto

class WordDtoToEntityMapper : BaseMapper<WordDto, WordEntity> {
    override fun map(dto: WordDto): WordEntity = WordEntity(
        word = dto.word.orEmpty(),
        definition = dto.results?.firstOrNull()?.definition.orEmpty(),
        pronunciation = dto.pronunciation?.all.orEmpty(),
        timestamp = System.currentTimeMillis()
    )
}