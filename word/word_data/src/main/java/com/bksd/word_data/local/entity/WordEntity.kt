package com.bksd.word_data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "words")
data class WordEntity(
    @PrimaryKey val word: String,
    val definition: String?,
    val pronunciation: String?,
    val isFavorite: Boolean = false,
    val timestamp: Long = System.currentTimeMillis() // for recent sort
)
