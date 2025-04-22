package com.bksd.core_data.api.service

import android.util.Log
import com.bksd.core_data.api.cache.WordApiCache
import com.bksd.core_data.api.executor.ApiRequestExecutor
import com.bksd.core_data.api.mapper.ExceptionMapper
import com.bksd.core_data.api.mapper.ResponseMapper
import com.bksd.core_data.dto.WordServiceResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Base class for Word API service implementations.
 * Provides common functionality for endpoint methods.
 *
 * @property requestExecutor Component for executing HTTP requests
 * @property responseMapper Component for mapping HTTP responses to domain objects
 * @property exceptionMapper Component for mapping exceptions
 * @property cache Component for caching responses
 */
abstract class BaseWordApiService(
    protected val requestExecutor: ApiRequestExecutor,
    protected val responseMapper: ResponseMapper,
    protected val exceptionMapper: ExceptionMapper,
    protected val cache: WordApiCache<WordServiceResponse>
) {
    /**
     * Creates a Flow that handles caching and API requests for a specific endpoint.
     *
     * @param endpoint The API endpoint to call
     * @param word The word to look up
     * @return A Flow emitting the API response
     */
    protected fun createApiFlow(endpoint: String, word: String): Flow<WordServiceResponse> = flow {
        val sanitizedWord = word.trim().lowercase()
            .also { Log.d("MyDebugger :: ", "Word: $it") }

        // Try to get from cache first
        cache.get(endpoint, sanitizedWord)?.let {
            emit(it)
                .also { a -> Log.d("MyDebugger ::", "Word in Cache :: $sanitizedWord") }
            return@flow
        }

        // If not in cache, make API request
        try {
            val response = requestExecutor.execute(endpoint, sanitizedWord)
            val wordResponse = responseMapper.mapResponse<WordServiceResponse>(response)

            // Cache the response
            cache.put(endpoint, sanitizedWord, wordResponse)
                .also { a -> Log.d("MyDebugger ::", "Word saved in Cache :: $sanitizedWord") }
            emit(wordResponse)
        } catch (e: Exception) {
            throw exceptionMapper.mapException(e, sanitizedWord)
        }
    }
}