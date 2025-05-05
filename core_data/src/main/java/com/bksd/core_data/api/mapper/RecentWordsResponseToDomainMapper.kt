package com.bksd.core_data.api.mapper

import com.bksd.core_data.dto.WordServiceResponse
import com.bksd.core_domain.mapper.ListMapper
import com.bksd.core_domain.model.WordInformation

class RecentWordsResponseToDomainMapper : ListMapper<WordServiceResponse, WordInformation> {
    override fun map(from: List<WordServiceResponse>): List<WordInformation> =
        from.map {
            WordInformation(
                text = it.word,
            )
        }
}