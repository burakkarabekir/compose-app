package com.bksd.word_data.local.datasource

import com.bksd.word_data.local.entity.WordEntity
import kotlinx.coroutines.flow.Flow

interface WordLocalDataSource {
    fun getRecentWords(): Flow<List<WordEntity>>?
    fun getFavoriteWords(): Flow<List<WordEntity>>?
    suspend fun getWordByName(word: String): WordEntity?
    suspend fun saveWord(word: WordEntity)
    suspend fun deleteWord(word: WordEntity)
    suspend fun clearWords()
}