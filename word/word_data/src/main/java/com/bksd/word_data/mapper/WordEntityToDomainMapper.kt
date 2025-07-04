package com.bksd.word_data.mapper

import com.bksd.core_domain.mapper.BaseMapper
import com.bksd.core_domain.mapper.ListMapper
import com.bksd.word_data.local.entity.WordEntity
import com.bksd.word_domain.model.WordDetail

class WordEntityToDomainListMapper : ListMapper<WordEntity, WordDetail> {
    override fun map(entities: List<WordEntity>): List<WordDetail> =
        entities.map { entity ->
            WordDetail(
                text = entity.word,
                definition = entity.definition,
                synonyms = entity.synonyms,
                antonyms = entity.antonyms,
                isFavorite = entity.isFavorite
            )
        }
}

class WordEntityToDomainMapper : BaseMapper<WordEntity, WordDetail> {
    override fun map(entity: WordEntity): WordDetail =
        WordDetail(
            text = entity.word,
            definition = entity.definition,
            synonyms = entity.synonyms,
            antonyms = entity.antonyms,
            isFavorite = entity.isFavorite
        )
}