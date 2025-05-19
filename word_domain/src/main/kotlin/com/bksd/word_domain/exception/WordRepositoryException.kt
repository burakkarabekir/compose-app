package com.bksd.word_domain.exception

/**
 * Base exception class for all word repository related errors.
 * Domain layer code should catch these exceptions to handle repository errors.
 */
sealed class WordRepositoryException(
    message: String,
    cause: Throwable? = null
) : Exception(message, cause)

/**
 * Exception thrown when a word is not found in the repository.
 */
class WordNotFoundRepositoryException(val word: String) :
    WordRepositoryException("Word not found in repository: '$word'")

/**
 * Exception thrown when authentication to the word service fails.
 */
class UnauthorizedRepositoryException :
    WordRepositoryException("Unauthorized access to dictionary service. Check your credentials.")

/**
 * Exception thrown when the requested feature requires a premium subscription.
 */
class SubscriptionRequiredRepositoryException :
    WordRepositoryException("This dictionary feature requires a premium subscription.")

/**
 * Exception thrown when the rate limit for word lookups is exceeded.
 */
class RateLimitedRepositoryException :
    WordRepositoryException("Word lookup rate limit exceeded. Please try again later.")

/**
 * Exception thrown when there's a network error during repository operations.
 */
class NetworkRepositoryException(message: String, cause: Throwable? = null) :
    WordRepositoryException("Network error while accessing dictionary: $message", cause)

/**
 * Exception thrown when a repository operation times out.
 */
class TimeoutRepositoryException(val timeoutMs: Long) :
    WordRepositoryException("Dictionary service request timed out after $timeoutMs ms")

/**
 * Exception thrown when there's a server error in the repository.
 */
class ServerRepositoryException(message: String, cause: Throwable? = null) :
    WordRepositoryException("Dictionary service error: $message", cause)

/**
 * Exception thrown when repository data cannot be parsed correctly.
 */
class DataParsingRepositoryException(message: String, cause: Throwable? = null) :
    WordRepositoryException("Failed to parse dictionary data: $message", cause)