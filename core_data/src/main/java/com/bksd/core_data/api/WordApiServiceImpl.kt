package com.bksd.core_data.api

import com.bksd.core_data.api.cache.WordApiCache
import com.bksd.core_data.api.executor.ApiRequestExecutor
import com.bksd.core_data.api.mapper.ExceptionMapper
import com.bksd.core_data.api.mapper.ResponseMapper
import com.bksd.core_data.api.service.BaseWordApiService
import com.bksd.core_data.dto.WordServiceResponse
import io.ktor.client.statement.HttpResponse
import kotlinx.coroutines.flow.Flow

/**
 * Implementation of WordApiService that delegates to specialized components.
 * Each component has a single responsibility, making the code more maintainable and testable.
 */
class WordApiServiceImpl(
    requestExecutor: ApiRequestExecutor,
    responseMapper: ResponseMapper,
    exceptionMapper: ExceptionMapper,
    cache: WordApiCache<WordServiceResponse>
) : BaseWordApiService(requestExecutor, responseMapper, exceptionMapper, cache), WordApiService {

    override suspend fun getWordInformation(word: String): Flow<WordServiceResponse> =
        createApiFlow(ApiRequestExecutor.ENDPOINT_WORD, word)

    override suspend fun getWordDefinition(word: String): Flow<WordServiceResponse> =
        createApiFlow(ApiRequestExecutor.ENDPOINT_DEFINITION, word)

    override suspend fun getWordSynonyms(word: String): Flow<WordServiceResponse> =
        createApiFlow(ApiRequestExecutor.ENDPOINT_SYNONYMS, word)

    override suspend fun getWordPronunciation(word: String): Flow<WordServiceResponse> =
        createApiFlow(ApiRequestExecutor.ENDPOINT_PRONUNCIATION, word)

    override suspend fun executeRawRequest(
        endpoint: String,
        word: String,
        params: Map<String, String>
    ): HttpResponse = requestExecutor.execute(endpoint, word, params)

    override fun clearCache() {
        cache.clear()
    }
}