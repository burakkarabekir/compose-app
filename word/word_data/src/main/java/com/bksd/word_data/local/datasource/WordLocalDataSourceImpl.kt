package com.bksd.word_data.local.datasource

import com.bksd.word_data.local.dao.WordDao
import com.bksd.word_data.local.entity.WordEntity
import kotlinx.coroutines.flow.Flow

class WordLocalDataSourceImpl(
    private val wordDao: WordDao
) : WordLocalDataSource {

    override fun getRecentWords(): Flow<List<WordEntity>>? = wordDao.getRecentWords()
    override fun getFavoriteWords(): Flow<List<WordEntity>>? = wordDao.getFavoriteWords()
    override suspend fun getWordByName(word: String): WordEntity? = wordDao.getWordByName(word)
    override suspend fun saveWord(word: WordEntity) = wordDao.upsert(word)
    override suspend fun updateFavoriteStatus(word: String, isFavorite: Boolean) =
        wordDao.updateFavoriteStatus(word, isFavorite)

    override suspend fun deleteWord(word: WordEntity) = wordDao.delete(word)
    override suspend fun clearWords() = wordDao.clearAll()
}