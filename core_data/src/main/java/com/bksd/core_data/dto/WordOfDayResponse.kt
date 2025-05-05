package com.bksd.core_data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Main response DTO for the Word API.
 * Contains comprehensive information about a word.
 */
@Serializable
data class WordOfDayResponse(
    @SerialName("word")
    val word: String? = null,
    @SerialName("frequency")
    val frequency: Double? = null,
    @SerialName("results")
    val results: List<WordOfDayResultDto>? = null,
    @SerialName("syllables")
    val syllables: SyllablesDto? = null,
    @SerialName("rhymes")
    val rhymes: WordOfDayRhymesDto? = null,
    @SerialName("pronunciation")
    val pronunciation: PronunciationDto? = null,
)

/**
 * Represents a single definition result from the Word API.
 */
@Serializable
data class WordOfDayResultDto(
    @SerialName("definition")
    val definition: String? = null,
    @SerialName("partOfSpeech")
    val partOfSpeech: String? = null,
    @SerialName("synonyms")
    val synonyms: List<String>? = null,
    @SerialName("antonyms")
    val antonyms: List<String>? = null,
    @SerialName("typeOf")
    val typeOf: List<String>? = null,
    @SerialName("hasTypes")
    val hasTypes: List<String>? = null,
    @SerialName("partOf")
    val partOf: List<String>? = null,
    @SerialName("derivation")
    val derivation: List<String>?
)

@Serializable
data class WordOfDayRhymesDto(
    @SerialName("all")
    val all: String?
)
