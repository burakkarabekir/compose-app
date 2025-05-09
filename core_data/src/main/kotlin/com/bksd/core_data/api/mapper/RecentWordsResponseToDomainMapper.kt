package com.bksd.core_data.api.mapper

import com.bksd.core_data.remote.dto.WordDto
import com.bksd.core_domain.mapper.ListMapper
import com.bksd.core_domain.model.WordDetail

class RecentWordsResponseToDomainMapper : ListMapper<WordDto, WordDetail> {
    override fun map(dto: List<WordDto>): List<WordDetail> =
        dto.map {
            WordDetail(
                text = it.word.orEmpty(),
                pronunciation = it.pronunciation?.all,
                definition = it.results?.firstOrNull()?.definition,
                synonyms = it.results?.firstOrNull()?.synonyms,
                antonyms = it.results?.firstOrNull()?.antonyms,
            )
        }
}