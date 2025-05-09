package com.bksd.core_data.api.service

import android.util.Log
import com.bksd.core_data.api.cache.WordApiCache
import com.bksd.core_data.api.executor.ApiRequestExecutor
import com.bksd.core_data.api.mapper.ExceptionMapper
import com.bksd.core_data.api.mapper.ResponseMapper
import com.bksd.core_data.config.JsonProvider

/**
 * Base class for Word API service implementations.
 * Provides common functionality for endpoint methods.
 *
 * @property requestExecutor Component for executing HTTP requests
 * @property responseMapper Component for mapping HTTP responses to domain objects
 * @property exceptionMapper Component for mapping exceptions
 * @property cache Component for caching responses (type-agnostic)
 */
abstract class BaseWordApiService(
    val requestExecutor: ApiRequestExecutor,
    val responseMapper: ResponseMapper,
    val exceptionMapper: ExceptionMapper,
    val cache: WordApiCache
) {
    suspend inline fun <reified R : Any> readFromCache(endpoint: String, key: String): R? =
        cache.get(endpoint, key) { raw ->
            JsonProvider.instance.decodeFromString<R>(raw)
        }

    suspend inline fun <reified R : Any> writeToCache(endpoint: String, key: String, value: R) {
        cache.put(endpoint, key, value) {
            JsonProvider.instance.encodeToString(it)
        }
    }

    protected suspend inline fun <reified R : Any> fetch(
        endpoint: String,
        pathParam: String? = null,
        queryParams: Map<String, String> = emptyMap()
    ): R {
        Log.d(
            "ComposeAppLogger :: BaseWordApiService",
            "Expecting response type: ${R::class.simpleName}"
        )
        val key = pathParam?.trim()?.lowercase().takeIf { it?.isNotBlank() == true }.orEmpty()
        Log.d("ComposeAppLogger :: BaseWordApiService", "Checking cache for: $key")

        readFromCache<R>(endpoint, key)?.let {
            Log.d("ComposeAppLogger :: BaseWordApiService", "Cache hit for: $key")
            Log.d("ComposeAppLogger :: readFromCache", "expected type: ${it::class.simpleName}")
            return it
        }

        return runCatching {
            requestExecutor.execute(endpoint, key, queryParams)
        }.mapCatching { response ->
            Log.d("ComposeAppLogger :: ResponseMapper", "status: ${response.status}")
            Log.d("ComposeAppLogger :: ResponseMapper", "expected type: ${R::class.simpleName}")
            responseMapper.mapResponse<R>(response)
        }.onSuccess { result ->
            writeToCache(endpoint, key, result)
            Log.d("ComposeAppLogger :: BaseWordApiService", "Fetched and cached: $key")
            return result
        }.onFailure { e ->
            throw exceptionMapper.mapException(e, key)
        }.getOrThrow()
    }
}