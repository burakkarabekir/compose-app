package com.bksd.core_data.api.mapper

import android.util.Log
import com.bksd.core_domain.exception.PaymentRequiredException
import com.bksd.core_domain.exception.UnauthorizedAccessException
import com.bksd.core_domain.exception.WordApiClientException
import com.bksd.core_domain.exception.WordApiNetworkException
import com.bksd.core_domain.exception.WordApiParsingException
import com.bksd.core_domain.exception.WordApiRateLimitException
import com.bksd.core_domain.exception.WordApiServerException
import com.bksd.core_domain.exception.WordApiTimeoutException
import com.bksd.core_domain.exception.WordNotFoundException
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.HttpRequestTimeoutException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.http.HttpStatusCode
import io.ktor.utils.io.CancellationException

/**
 * Component responsible for mapping exceptions that occur during API requests
 * to domain-specific exceptions.
 */
class ExceptionMapper {
    fun mapException(throwable: Throwable, word: String? = null): Exception = when (throwable) {
        is ClientRequestException -> mapClientException(throwable, word ?: "word :: null")
        is ServerResponseException -> WordApiServerException(
            "Server error: ${throwable.response.status}",
            throwable
        )

        is HttpRequestTimeoutException -> WordApiTimeoutException(20_000L)
        is WordApiParsingException -> throwable
        is CancellationException -> throwable
        else -> WordApiNetworkException(
            "Unexpected error during API request: ${throwable.message ?: "Unknown"}",
            throwable
        )
    }.also {
        Log.d("ComposeAppLogger", "ExceptionMapper :: mapException: $it")
    }

    private fun mapClientException(exception: ClientRequestException, word: String): Exception =
        when (exception.response.status) {
            HttpStatusCode.NotFound -> WordNotFoundException(word)
            HttpStatusCode.Unauthorized -> UnauthorizedAccessException()
            HttpStatusCode.PaymentRequired -> PaymentRequiredException()
            HttpStatusCode.TooManyRequests -> WordApiRateLimitException()
            HttpStatusCode.BadRequest -> WordApiClientException(
                "Invalid request: ${exception.message}",
                exception
            )
            else -> WordApiNetworkException("API error: ${exception.response.status}", exception)
        }
}