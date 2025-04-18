package com.bksd.core_domain.result

sealed interface Result<out T> {
    data object Loading : Result<Nothing>
    data class Success<T>(val data: T) : Result<T>
    data class Error(val exception: Exception, val message: String) : Result<Nothing>
}