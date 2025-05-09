package com.bksd.core_data.dto

import kotlinx.serialization.Serializable

/**
 * Main response DTO for the Word API.
 * Contains comprehensive information about a word.
 */
@Serializable
data class WordDto(
    val word: String? = null,
    val results: List<ResultDto>? = null,
    val syllables: SyllablesDto? = null,
    val pronunciation: PronunciationDto? = null,
    val frequency: Double? = null,
    val rhymes: RhymesDto? = null
)






