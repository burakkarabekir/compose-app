package com.bksd.word_data.remote.dto

import kotlinx.serialization.Serializable

/**
 * Represents rhyming information for a word.
 */
@Serializable
data class RhymesDto(
    val all: List<String>? = null,
    val perfect: List<String>? = null,
    val near: List<String>? = null
)