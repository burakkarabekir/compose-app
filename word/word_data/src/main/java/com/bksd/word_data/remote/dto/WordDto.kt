package com.bksd.word_data.remote.dto

import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

/**
 * Main response DTO for the Word API.
 * Contains comprehensive information about a word.
 */
@Serializable
data class WordDto(
    @PrimaryKey val word: String,
    val results: List<ResultDto>? = null,
    val syllables: SyllablesDto? = null,
    val frequency: Double? = null,
    val isFavorite: Boolean = false
)






