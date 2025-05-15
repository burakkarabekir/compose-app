package com.bksd.core_data.api

import com.bksd.core_data.api.cache.WordApiCache
import com.bksd.core_data.api.executor.ApiRequestExecutor
import com.bksd.core_data.api.executor.ApiRequestExecutor.Companion.ENDPOINT_WORD
import com.bksd.core_data.api.mapper.ExceptionMapper
import com.bksd.core_data.api.mapper.ResponseMapper
import com.bksd.core_data.api.service.BaseWordApiService
import com.bksd.core_data.remote.dto.WordDto
import io.ktor.client.statement.HttpResponse

/**
 * Implementation of WordApiService that delegates to specialized components.
 * Each component has a single responsibility, making the code more maintainable and testable.
 */
class WordApiServiceImpl(
    requestExecutor: ApiRequestExecutor,
    responseMapper: ResponseMapper,
    exceptionMapper: ExceptionMapper,
    cache: WordApiCache
) : BaseWordApiService(requestExecutor, responseMapper, exceptionMapper, cache), WordApiService {

    override suspend fun fetchWord(word: String): WordDto =
        fetch(ENDPOINT_WORD, pathParam = word)

    override suspend fun fetchWordOfDay(): WordDto =
        fetch(
            endpoint = ENDPOINT_WORD,
            queryParams = mapOf(KEY_QUERY_PARAM_RANDOM to VALUE_QUERY_PARAM_RANDOM)
        )

    override suspend fun getWordDefinition(word: String): WordDto =
        fetch(ApiRequestExecutor.ENDPOINT_DEFINITION, pathParam = word)

    override suspend fun getWordSynonyms(word: String): WordDto =
        fetch(ApiRequestExecutor.ENDPOINT_SYNONYMS, pathParam = word)

    override suspend fun getWordPronunciation(word: String): WordDto =
        fetch(ApiRequestExecutor.ENDPOINT_PRONUNCIATION, pathParam = word)

    override suspend fun executeRawRequest(
        endpoint: String,
        word: String,
        params: Map<String, String>
    ): HttpResponse = requestExecutor.execute(endpoint, word, params)

    override fun clearCache() {
        cache.clear()
    }

    companion object {
        private const val KEY_QUERY_PARAM_RANDOM = "random"
        private const val VALUE_QUERY_PARAM_RANDOM = "true"
    }
}