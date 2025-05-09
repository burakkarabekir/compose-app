package com.bksd.core_domain.model

data class WordOfDay(
    val word: String?,
    val pronunciation: String?,
    val synonyms: List<String>?,
    val antonyms: List<String>?,
    val definition: String?
)