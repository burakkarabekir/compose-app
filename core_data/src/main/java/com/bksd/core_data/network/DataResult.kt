package com.bksd.core_data.network

import io.ktor.client.HttpClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

/**
 * Represents the state of data operations at the data layer.
 * This is specific to network or database operations.
 */
sealed class DataState<out T> {
    data object Loading : DataState<Nothing>()
    data class Success<T>(val data: T) : DataState<T>()
    data class Error(
        val throwable: Throwable,
        val errorCode: Int? = null,
        val errorMessage: String? = null
    ) : DataState<Nothing>()

    /**
     * Checks if this result is successful.
     */
    val isSuccess: Boolean get() = this is Success

    /**
     * Checks if this result is loading.
     */
    val isLoading: Boolean get() = this is Loading

    /**
     * Checks if this result is an error.
     */
    val isError: Boolean get() = this is Error

    /**
     * Gets the data if this result is a success, otherwise returns null.
     */
    fun getOrNull(): T? = when (this) {
        is Success -> data
        else -> null
    }
}

/**
 * Wraps a Ktor request in a Flow<DataState<T>>
 */
abstract class BaseApiService(protected val client: HttpClient) {
    protected fun <T> request(block: suspend () -> T): Flow<DataState<T>> =
        flow {
            emit(DataState.Loading)
            emit(DataState.Success(block()))
        }.catch { e ->
            emit(DataState.Error(e, extractErrorCode(e), e.message))
        }

    /**
     * Extracts error code from throwable if available
     */
    private fun extractErrorCode(throwable: Throwable): Int? {
        // Extract HTTP status code or other error codes if available
        // This is just a placeholder implementation
        return when (throwable) {
            is io.ktor.client.plugins.ClientRequestException -> throwable.response.status.value
            is io.ktor.client.plugins.ServerResponseException -> throwable.response.status.value
            else -> null
        }
    }
}
