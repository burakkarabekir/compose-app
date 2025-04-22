package com.bksd.core_domain.extension

import com.bksd.core_domain.result.DomainResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

fun <T> Flow<T>.asResult(): Flow<DomainResult<T>> = flow {
    try {
        emit(DomainResult.Loading)
        collect { value ->
            emit(DomainResult.Success(value))
        }
    } catch (e: Exception) {
        emit(
            DomainResult.Error(
                e, e.message
                    ?: "Unknown Error"
            )
        )
    }
}