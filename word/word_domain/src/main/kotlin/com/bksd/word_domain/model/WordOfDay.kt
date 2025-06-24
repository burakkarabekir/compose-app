package com.bksd.word_domain.model

data class WordOfDay(
    val word: String?,
    val synonyms: List<String>?,
    val antonyms: List<String>?,
    val definition: String?,
    val isFavorite: Boolean
)