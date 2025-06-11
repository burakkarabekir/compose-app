package com.bksd.core_data.model

import kotlinx.serialization.Serializable

@Serializable
sealed interface NetworkResult<out T> {
    data class Success<T>(val data: T) : NetworkResult<T>

    data class Error(
        val code: Int? = null,
        val message: String? = null
    ) : NetworkResult<Nothing>
}
