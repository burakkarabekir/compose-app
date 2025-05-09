package com.bksd.core_data.dto

import kotlinx.serialization.Serializable

/**
 * Represents syllable information for a word.
 */
@Serializable
data class SyllablesDto(
    val count: Int? = null,
    val list: List<String>? = null
)