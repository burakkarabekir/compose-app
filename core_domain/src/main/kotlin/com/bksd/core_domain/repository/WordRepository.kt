package com.bksd.core_domain.repository

import com.bksd.core_domain.dto.WordInformation
import com.bksd.core_domain.result.DomainResult
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface for word-related operations.
 * Provides methods to fetch various aspects of word information.
 */
interface WordRepository {
    /**
     * Gets complete information about a word including definitions, synonyms,
     * pronunciation, and other available data.
     *
     * @param word The word to look up
     * @return Flow emitting the complete word information
     * @throws WordRepositoryException if an error occurs during retrieval
     */
    suspend fun getWordInformation(word: String): Flow<DomainResult<WordInformation>>

    /**
     * Gets only the definitions for a word.
     *
     * @param word The word to look up
     * @return Flow emitting the word definition information
     * @throws WordRepositoryException if an error occurs during retrieval
     *//*
    suspend fun getWordDefinition(word: String): Flow<WordDefinition>
    
    */
    /**
     * Gets only the synonyms for a word.
     *
     * @param word The word to look up
     * @return Flow emitting the word synonyms
     * @throws WordRepositoryException if an error occurs during retrieval
     *//*
    suspend fun getWordSynonyms(word: String): Flow<WordSynonyms>
    
    */
    /**
     * Gets only the pronunciation for a word.
     *
     * @param word The word to look up
     * @return Flow emitting the word pronunciation
     * @throws WordRepositoryException if an error occurs during retrieval
     *//*
    suspend fun getWordPronunciation(word: String): Flow<WordPronunciation>
    
    */
    /**
     * Checks if a word exists in the dictionary.
     *
     * @param word The word to check
     * @return Flow emitting true if the word exists, false otherwise
     * @throws WordRepositoryException if an error occurs during the check
     *//*
    suspend fun wordExists(word: String): Flow<Boolean>*/
}