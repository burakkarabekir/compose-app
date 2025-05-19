package com.bksd.word_ui.model

sealed interface WordDetailUi {
    val word: String
    val pronunciation: String?
    val definition: String?
    val synonyms: List<String>?
    val antonyms: List<String>?
    val isFavorite: Boolean
}

data class WordDetailCardUi(
    override val word: String,
    override val pronunciation: String?,
    override val definition: String?,
    override val synonyms: List<String>?,
    override val antonyms: List<String>?,
    override val isFavorite: Boolean = false,
) : WordDetailUi

data class WordOfDayDetailCardUi(
    override val word: String,
    override val pronunciation: String?,
    override val definition: String?,
    override val synonyms: List<String>?,
    override val antonyms: List<String>?,
    override val isFavorite: Boolean = false,
    val label: String
) : WordDetailUi