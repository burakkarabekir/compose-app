package com.bksd.word_domain.model

data class WordDetail(
    val text: String,
    val pronunciation: String?,
    val definition: String?,
    val synonyms: List<String>?,
    val antonyms: List<String>?,
)