package com.bksd.core_domain.result

sealed interface DomainResult<out T> {
    data object Loading : DomainResult<Nothing>
    data class Success<T>(val data: T) : DomainResult<T>
    data class Error(val exception: Exception, val message: String) : DomainResult<Nothing>
    data object Empty : DomainResult<Nothing>
}