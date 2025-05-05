package com.bksd.core_data.api.service

import android.util.Log
import com.bksd.core_data.api.cache.WordApiCache
import com.bksd.core_data.api.executor.ApiRequestExecutor
import com.bksd.core_data.api.mapper.ExceptionMapper
import com.bksd.core_data.api.mapper.ResponseMapper
import com.bksd.core_data.config.JsonProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.encodeToString

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
    /**
     * Creates a flow that fetches data from cache or API.
     *
     * @param endpoint The API endpoint to call
     * @param pathParam The path parameter (e.g., word) to use in the request
     * @param queryParams Optional query parameters for the API call
     */
    protected suspend inline fun <reified R : Any> createApiFlow(
        endpoint: String,
        pathParam: String? = null,
        queryParams: Map<String, String> = emptyMap()
    ): Flow<R> = flow {
        val sanitizedPath = pathParam?.trim()?.lowercase()?.takeIf { it.isNotBlank() }.orEmpty()
        Log.d("ComposeAppLogger :: BaseWordApiService", "Lookup pathParam: $sanitizedPath")

        // Try reading from cache (raw JSON) and deserializing lazily
        cache.get(endpoint, sanitizedPath) { raw ->
            JsonProvider.instance.decodeFromString<R>(raw)
        }?.let { cachedValue ->
            Log.d("ComposeAppLogger :: BaseWordApiService", "Cache hit for $sanitizedPath")
            emit(cachedValue)
            return@flow
        }

        // Not in cache — fetch from API
        try {
            val response = requestExecutor.execute(endpoint, sanitizedPath, queryParams)
            val result: R = responseMapper.mapResponse(response)
            val readyForCache = pathParam?.trim()?.lowercase()?.takeIf { it.isNotBlank() } ?: "test"
            // Serialize and store in cache
            cache.put(endpoint, readyForCache, result) { obj ->
                JsonProvider.instance.encodeToString(obj)
            }
            Log.d("ComposeAppLogger :: BaseWordApiService", "Cache saved for $sanitizedPath")
            Log.d("ComposeAppLogger :: BaseWordApiService", "Cache saved for $readyForCache")

            emit(result)
        } catch (e: Exception) {
            throw exceptionMapper.mapException(e, sanitizedPath)
        }
    }

    protected suspend inline fun <reified R : Any> callApi(
        endpoint: String,
        pathParam: String? = null,
        queryParams: Map<String, String> = emptyMap()
    ): R {
        val sanitizedPath = pathParam?.trim()?.lowercase()?.takeIf { it.isNotBlank() }.orEmpty()
        Log.d("ComposeAppLogger :: BaseWordApiService", "Lookup pathParam: $sanitizedPath")

        // Try reading from cache (raw JSON) and deserializing lazily
        cache.get(endpoint, sanitizedPath) { raw ->
            JsonProvider.instance.decodeFromString<R>(raw)
        }?.let { cachedValue ->
            Log.d("ComposeAppLogger :: BaseWordApiService", "Cache hit for $sanitizedPath")
            return cachedValue

        }

        // Not in cache — fetch from API
        try {
            val response = requestExecutor.execute(endpoint, sanitizedPath, queryParams)
            val result: R = responseMapper.mapResponse(response)

            // Serialize and store in cache
            cache.put(endpoint, sanitizedPath, result) { obj ->
                JsonProvider.instance.encodeToString(obj)
            }
            Log.d("ComposeAppLogger :: BaseWordApiService", "Cache saved for $sanitizedPath")

            return result
        } catch (e: Exception) {
            throw exceptionMapper.mapException(e, sanitizedPath)
        }
    }
}