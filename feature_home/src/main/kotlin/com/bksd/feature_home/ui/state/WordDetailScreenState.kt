package com.bksd.feature_home.ui.state

data class WordDetailScreenState(
    val word: String = "",
    val definition: String? = null,
    val synonyms: List<String>? = null,
    val antonyms: List<String>? = null,
    val examples: List<String>? = null,
    val pronunciation: String? = null
)