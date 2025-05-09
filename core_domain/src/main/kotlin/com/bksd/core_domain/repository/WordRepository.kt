package com.bksd.core_domain.repository

import com.bksd.core_domain.model.WordDetailModel
import com.bksd.core_domain.model.WordOfDayModel
import com.bksd.core_domain.result.DomainResult
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface for word-related operations.
 * Provides methods to fetch various aspects of word information.
 */
interface WordRepository {
    suspend fun getWordOfDay(): Flow<DomainResult<WordOfDayModel>>
    suspend fun getRecentWords(): Flow<DomainResult<List<WordDetailModel>>>
    suspend fun getWordInformation(word: String): Flow<DomainResult<WordDetailModel>>
    /*
    suspend fun getWordDefinition(word: String): Flow<WordDefinition>
    suspend fun getWordSynonyms(word: String): Flow<WordSynonyms>
    suspend fun getWordPronunciation(word: String): Flow<WordPronunciation>
    suspend fun wordExists(word: String): Flow<Boolean>*/
}