package com.bksd.core_domain.model

data class WordDetailModel(
    val text: String,
    val pronunciation: String?,
    val definition: String?,
    val synonyms: List<String>?,
    val antonyms: List<String>?,
)