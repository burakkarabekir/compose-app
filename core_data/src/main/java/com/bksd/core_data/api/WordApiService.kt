package com.bksd.core_data.api

import com.bksd.core_data.dto.WordOfDayResponse
import com.bksd.core_data.dto.WordServiceResponse
import io.ktor.client.statement.HttpResponse
import kotlinx.coroutines.flow.Flow

/**
 * Interface defining the Word API service operations.
 * Provides methods to interact with various Word API endpoints.
 */
interface WordApiService {
    /**
     * Fetches complete word information including definitions, synonyms, pronunciation, etc.
     *
     * @param word The word to look up
     * @return A Flow emitting the complete word information
     */
    suspend fun getWordInformation(word: String): Flow<WordServiceResponse>
    suspend fun getWordOfDay(): WordOfDayResponse

    /**
     * Fetches only the definitions for a word.
     *
     * @param word The word to look up
     * @return A Flow emitting the word with its definitions
     */
    suspend fun getWordDefinition(word: String): Flow<WordServiceResponse>

    /**
     * Fetches only synonyms for a word.
     *
     * @param word The word to look up
     * @return A Flow emitting the word with its synonyms
     */
    suspend fun getWordSynonyms(word: String): Flow<WordServiceResponse>

    /**
     * Fetches only pronunciation information for a word.
     *
     * @param word The word to look up
     * @return A Flow emitting the word with its pronunciation
     */
    suspend fun getWordPronunciation(word: String): Flow<WordServiceResponse>

    /**
     * Performs a raw HTTP request to the Word API.
     * Useful for custom endpoints or direct response handling.
     *
     * @param endpoint The specific API endpoint
     * @param word The word to look up
     * @param params Optional query parameters
     * @return The raw HTTP response
     */
    suspend fun executeRawRequest(
        endpoint: String,
        word: String,
        params: Map<String, String> = emptyMap()
    ): HttpResponse

    /**
     * Clears any cached responses.
     */
    fun clearCache()
}