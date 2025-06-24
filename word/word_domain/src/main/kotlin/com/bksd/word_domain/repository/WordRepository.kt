package com.bksd.word_domain.repository

import com.bksd.core_domain.result.DomainResult
import com.bksd.word_domain.model.WordDetail
import com.bksd.word_domain.model.WordOfDay
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface for word-related operations.
 * Provides methods to fetch various aspects of word information.
 */
interface WordRepository {
    suspend fun getWordOfDay(): Flow<DomainResult<WordOfDay>>
    suspend fun getWordDetail(word: String): Flow<DomainResult<WordDetail>>
    suspend fun getRecentWords(): Flow<DomainResult<List<WordDetail>>>
    suspend fun fetchWord(word: String): Flow<DomainResult<WordDetail>>
    suspend fun setWordFavorite(word: String, isFavorite: Boolean)
    /*
    suspend fun getWordDefinition(word: String): Flow<WordDefinition>
    suspend fun getWordSynonyms(word: String): Flow<WordSynonyms>
    suspend fun getWordPronunciation(word: String): Flow<WordPronunciation>
    suspend fun wordExists(word: String): Flow<Boolean>*/
}