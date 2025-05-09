package com.bksd.core_data.config

import com.bksd.core_data.BuildConfig

/**
 * Configuration class for Word API settings.
 * This centralizes all configuration parameters for the API.
 *
 * @property baseUrl Base URL of the Word API
 * @property apiKey API key for authentication
 * @property timeoutMs Timeout for requests in milliseconds
 * @property retryCount Number of times to retry failed requests
 * @property enableLogging Whether to enable request/response logging
 * @property cacheSize Size of the in-memory response cache (in items)
 * @property cacheTtl Time-to-live for cached responses (in milliseconds)
 */
data class WordApiConfig(
    val baseUrl: String = BuildConfig.BASE_URL,
    val apiKey: String = BuildConfig.VALUE_API_KEY,
    val timeoutMs: Long = 20_000L,
    val retryCount: Int = 3,
    val enableLogging: Boolean = true,
    val cacheSize: Int = 100,
    val cacheTtl: Long = 3600_000L, // 1 hour
    val isDebug: Boolean = BuildConfig.DEBUG
)