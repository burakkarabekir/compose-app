package com.bksd.core_domain.exception

sealed class BusinessException : Exception() {
    data class NetworkException(override val message: String) : BusinessException()
    data class ValidationException(override val message: String) : BusinessException()
    data class NotFoundException(override val message: String) : BusinessException()
    data class UnAuthorizedException(override val message: String) : BusinessException()
}