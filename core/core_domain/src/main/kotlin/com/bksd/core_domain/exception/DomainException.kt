package com.bksd.core_domain.exception

/**
 * Base exception class for all Word API related exceptions.
 *
 * @param message A descriptive error message
 * @param cause The underlying cause of the exception
 */

sealed class WordApiException(
    message: String,
    cause: Throwable? = null
) : Exception(message, cause)

/**
 * Exception thrown when a requested word is not found in the API.
 *
 * @param word The word that was not found
 */
class WordNotFoundException(word: String) :
    WordApiException("Word not found: '$word'")

/**
 * Exception thrown when the API request is unauthorized.
 * This typically indicates an invalid or missing API key.
 */
class UnauthorizedAccessException :
    WordApiException("Unauthorized access to Word API. Check your API key.")

/**
 * Exception thrown when the API request requires payment or subscription.
 */
class PaymentRequiredException :
    WordApiException("This feature requires a premium subscription to Word API.")

/**
 * Exception thrown when the API rate limit has been exceeded.
 */
class WordApiRateLimitException :
    WordApiException("Word API rate limit exceeded. Try again later.")

/**
 * Exception thrown when there is an error on the server side.
 *
 * @param message A descriptive error message
 * @param cause The underlying cause of the exception
 */
class WordApiServerException(
    message: String,
    cause: Throwable? = null
) : WordApiException(message, cause)

/**
 * Exception thrown when there is an error on the client side.
 *
 * @param message A descriptive error message
 * @param cause The underlying cause of the exception
 */
class WordApiClientException(
    message: String,
    cause: Throwable? = null
) : WordApiException(message, cause)

/**
 * Exception thrown when the API response cannot be parsed.
 *
 * @param message A descriptive error message
 * @param cause The underlying cause of the exception
 */
class WordApiParsingException(
    message: String,
    cause: Throwable? = null
) : WordApiException("Failed to parse API response: $message", cause)

/**
 * Exception thrown when a network error occurs during an API request.
 *
 * @param message A descriptive error message
 * @param cause The underlying cause of the exception
 */
class WordApiNetworkException(
    message: String,
    cause: Throwable? = null
) : WordApiException("Network error during API request: $message", cause)

/**
 * Exception thrown when a request to the API times out.
 *
 * @param timeoutMs The timeout duration in milliseconds
 */
class WordApiTimeoutException(timeoutMs: Long) :
    WordApiException("API request timed out after $timeoutMs ms")