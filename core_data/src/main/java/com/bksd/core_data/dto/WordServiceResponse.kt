package com.bksd.core_data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Main response DTO for the Word API.
 * Contains comprehensive information about a word.
 */
@Serializable
data class WordServiceResponse(
    val word: String,
    val results: List<ResultDto> = emptyList(),
    val syllables: SyllablesDto? = null,
    val pronunciation: PronunciationDto? = null,
    val frequency: Double? = null,
    val rhymes: RhymesDto? = null
)

/**
 * Represents a single definition result from the Word API.
 */
@Serializable
data class ResultDto(
    val definition: String,
    @SerialName("partOfSpeech")
    val partOfSpeech: String? = null,
    val synonyms: List<String>? = null,
    val antonyms: List<String>? = null,
    val examples: List<String>? = null,
    val typeOf: List<String>? = null,
    val hasTypes: List<String>? = null,
    val partOf: List<String>? = null,
    val memberOf: List<String>? = null,
    val instanceOf: List<String>? = null,
    val hasInstances: List<String>? = null,
    val similarTo: List<String>? = null,
    val also: List<String>? = null,
    val entails: List<String>? = null,
    val inCategory: List<String>? = null,
    val regions: List<String>? = null,
    val usageOf: List<String>? = null,
    val inRegister: List<String>? = null,
    val derivation: List<String>? = null
)

/**
 * Represents syllable information for a word.
 */
@Serializable
data class SyllablesDto(
    val count: Int,
    val list: List<String> = emptyList()
)

/**
 * Represents pronunciation information for a word.
 */
@Serializable
data class PronunciationDto(
    val all: String? = null,
    val noun: String? = null,
    val verb: String? = null,
    val adjective: String? = null,
    val adverb: String? = null,
    @SerialName("audio_url")
    val audioUrl: String? = null,
    val dialect: String? = null
)

/**
 * Represents rhyming information for a word.
 */
@Serializable
data class RhymesDto(
    val all: List<String>? = null,
    val perfect: List<String>? = null,
    val near: List<String>? = null
)
