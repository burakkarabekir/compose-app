package com.bksd.core_data.api.mapper

import com.bksd.core_data.dto.WordDto
import com.bksd.core_domain.mapper.ListMapper
import com.bksd.core_domain.model.WordDetailModel

class RecentWordsResponseToDomainMapper : ListMapper<WordDto, WordDetailModel> {
    override fun map(dto: List<WordDto>): List<WordDetailModel> =
        dto.map {
            WordDetailModel(
                text = it.word.orEmpty(),
                pronunciation = it.pronunciation?.all,
                definition = it.results?.firstOrNull()?.definition,
                synonyms = it.results?.firstOrNull()?.synonyms,
                antonyms = it.results?.firstOrNull()?.antonyms,
            )
        }
}