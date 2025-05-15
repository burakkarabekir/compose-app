package com.bksd.core_data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bksd.core_data.local.entity.WordEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WordDao {

    @Query("SELECT * FROM words WHERE word = :word LIMIT 1")
    suspend fun getWordByName(word: String): WordEntity?

    @Query("SELECT * FROM words ORDER BY timestamp DESC LIMIT 20")
    fun getRecentWords(): Flow<List<WordEntity>>?

    @Query("SELECT * FROM words WHERE isFavorite = 1")
    fun getFavoriteWords(): Flow<List<WordEntity>>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(word: WordEntity)

    @Delete
    suspend fun delete(word: WordEntity)

    @Query("DELETE FROM words")
    suspend fun clearAll()
}