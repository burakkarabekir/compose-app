package com.bksd.core_data.dto

import kotlinx.serialization.Serializable

/**
 * Represents a single definition result from the Word API.
 */
@Serializable
data class ResultDto(
    val definition: String? = null,
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