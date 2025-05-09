package com.bksd.core_data.model

import kotlinx.serialization.Serializable

@Serializable
sealed interface NetworkResponse<out T> {
    data class Success<T>(val data: T) : NetworkResponse<T>

    data class Error(
        val code: Int? = null,
        val message: String? = null
    ) : NetworkResponse<Nothing>
}
