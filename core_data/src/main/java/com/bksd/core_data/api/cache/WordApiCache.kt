package com.bksd.core_data.api.cache

import com.bksd.core_data.extension.InMemoryCache

/**
 * Component responsible for caching API responses to reduce network requests.
 * Uses endpoint and word as the cache key components.
 *
 * @param T The type of objects to cache
 * @property cache The underlying cache implementation
 */
class WordApiCache<T : Any>(
    private val cache: InMemoryCache<String, T>
) {
    /**
     * Gets a cached response for the given endpoint and word.
     *
     * @param endpoint The API endpoint
     * @param word The word that was looked up
     * @return The cached response or null if not in cache
     */
    fun get(endpoint: String, word: String): T? {
        return cache.get(createCacheKey(endpoint, word))
    }

    /**
     * Stores a response in the cache.
     *
     * @param endpoint The API endpoint
     * @param word The word that was looked up
     * @param response The response to cache
     */
    fun put(endpoint: String, word: String, response: T) {
        cache.put(createCacheKey(endpoint, word), response)
    }

    /**
     * Clears all entries from the cache.
     */
    fun clear() {
        cache.clear()
    }

    /**
     * Creates a cache key from endpoint and word.
     *
     * @param endpoint The API endpoint
     * @param word The word that was looked up
     * @return A cache key string
     */
    private fun createCacheKey(endpoint: String, word: String): String {
        return "$endpoint:${word.trim().lowercase()}"
    }

    companion object {
        /**
         * Creates a new WordApiCache with default settings.
         *
         * @param T The type of objects to cache
         * @param maxSize Maximum number of entries in the cache
         * @param ttlMs Time-to-live in milliseconds
         * @return A new WordApiCache instance
         */
        inline fun <reified T : Any> create(
            maxSize: Int = 100,
            ttlMs: Long = 3600_000L
        ): WordApiCache<T> {
            return WordApiCache(InMemoryCache(maxSize, ttlMs))
        }
    }
}
