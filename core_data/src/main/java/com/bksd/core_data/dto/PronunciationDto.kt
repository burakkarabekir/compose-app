package com.bksd.core_data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

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