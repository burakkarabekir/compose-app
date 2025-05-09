package com.bksd.core_data.extension

import com.bksd.core_data.network.DataState
import com.bksd.core_domain.result.DomainResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Maps a DataState to a DomainResult.
 * This is typically used when crossing the boundary from data layer to domain layer.
 */
fun <T, R> DataState<T>.toDomainResult(transform: (T) -> R): DomainResult<R> {
    return when (this) {
        is DataState.Loading -> DomainResult.Loading
        is DataState.Success -> DomainResult.Success(transform(data))
        is DataState.Error -> DomainResult.Error(
            exception = throwable as? Exception ?: Exception(throwable),
            message = errorMessage ?: throwable.message ?: "Unknown error"
        )
    }
}

/**
 * Maps a Flow of DataState to a Flow of DomainResult.
 * This is typically used when crossing the boundary from data layer to domain layer.
 */
fun <T, R> Flow<DataState<T>>.toDomainResultFlow(transform: (T) -> R): Flow<DomainResult<R>> {
    return map { dataResult ->
        dataResult.toDomainResult(transform)
    }
}

/**
 * Converts a Flow of T to a Flow of DataState<T>.
 * Useful for wrapping simple flows with the DataState type.
 */
fun <T> Flow<T>.asDataState(): Flow<DataState<T>> {
    return map { DataState.Success(it) }
}

