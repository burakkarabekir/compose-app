package com.bksd.core_ui.model

data class WordDetailCardUi(
    val word: String,
    val pronunciation: String?,
    val definition: String?,
    val synonyms: List<String>?,
    val antonyms: List<String>?,
    val label: String? = null,
)