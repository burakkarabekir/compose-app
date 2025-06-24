package com.bksd.word_data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "words")
data class WordEntity(
    @PrimaryKey val word: String,
    val definition: String?,
    val isFavorite: Boolean = false,
    val synonyms: List<String>? = emptyList(),
    val antonyms: List<String>? = emptyList(),
    val timestamp: Long = System.currentTimeMillis() // for recent sort
)
