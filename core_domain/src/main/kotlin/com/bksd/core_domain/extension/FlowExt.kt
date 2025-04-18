package com.bksd.core_domain.extension

import com.bksd.core_domain.result.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

fun <T> Flow<T>.asResult(): Flow<Result<T>> = flow {
    try {
        emit(Result.Loading)
        collect { value ->
            emit(Result.Success(value))
        }
    } catch (e: Exception) {
        emit(Result.Error(e, e.message ?: "Unknown Error"))
    }
}