package com.bksd.word_ui.model

sealed interface WordDetailUi {
    val word: String?
    val definition: String?
    val synonyms: List<String>?
    val antonyms: List<String>?
    val isFavorite: Boolean
    fun setFavorite(newStatus: Boolean): WordDetailUi
}

data class WordDetailCardUi(
    override val word: String = "",
    override val definition: String? = "",
    override val synonyms: List<String>? = emptyList(),
    override val antonyms: List<String>? = emptyList(),
    override val isFavorite: Boolean = false,
) : WordDetailUi {
    override fun setFavorite(newStatus: Boolean): WordDetailUi = this.copy(isFavorite = newStatus)
}

data class WordOfDayDetailCardUi(
    override val word: String,
    override val definition: String?,
    override val synonyms: List<String>?,
    override val antonyms: List<String>?,
    override val isFavorite: Boolean = false,
    val label: String
) : WordDetailUi {
    override fun setFavorite(newStatus: Boolean): WordDetailUi = this.copy(isFavorite = newStatus)
}