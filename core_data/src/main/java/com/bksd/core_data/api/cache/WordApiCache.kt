package com.bksd.core_data.api.cache

import com.bksd.core_data.extension.InMemoryCache

/**
 * A type-agnostic cache interface for API responses.
 */
interface WordApiCache {
    /**
     * Retrieves a cached JSON string for the given endpoint and key, and deserializes it.
     *
     * @param T The target type of the cached object.
     * @param endpoint The API endpoint.
     * @param key The cache key (e.g. sanitized word).
     * @param deserializer Function to convert the raw JSON into T.
     * @return A deserialized object of type T or null if not cached.
     */
    suspend fun <T : Any> get(
        endpoint: String,
        key: String,
        deserializer: (String) -> T
    ): T?

    /**
     * Serializes and stores the given object under the composite cache key.
     *
     * @param T The type of the object to cache.
     * @param endpoint The API endpoint.
     * @param key The cache key.
     * @param value The object to cache.
     * @param serializer Function to convert the object to JSON.
     */
    suspend fun <T : Any> put(
        endpoint: String,
        key: String,
        value: T,
        serializer: (T) -> String
    )

    /** Clears all entries from the cache. */
    fun clear()
}

/**
 * In-memory implementation of [WordApiCache], storing raw JSON strings.
 */
class InMemoryWordApiCache(
    private val cache: InMemoryCache<String, String>
) : WordApiCache {

    init {
        cache.put("endpoint1", "test1")
        cache.put("endpoint2", "test2")
        cache.put("endpoint3", "test3")
        cache.put("endpoint4", "test4")
        cache.put("endpoint5", "test5")
    }

    override suspend fun <T : Any> get(
        endpoint: String,
        key: String,
        deserializer: (String) -> T
    ): T? {
        val raw = cache.get("$endpoint:$key") ?: return null
        return deserializer(raw)
    }

    override suspend fun <T : Any> put(
        endpoint: String,
        key: String,
        value: T,
        serializer: (T) -> String
    ) {
        val raw = serializer(value)
        cache.put("$endpoint:$key", raw)
    }

    /**
     * Returns a list of all cached values as deserialized objects.
     *
     * @param T The target type of each cached object.
     * @param deserializer Function to convert each raw JSON string into T.
     * @return A list of all cached entries.
     */
    suspend fun <T : Any> getAll(deserializer: (String) -> T): List<T> =
        cache.snapshot().values.mapNotNull { raw ->
            try {
                deserializer(raw)
            } catch (e: Exception) {
                null
            }
        }


    override fun clear() {
        cache.clear()
    }

    companion object {
        /**
         * Convenience creator for an in-memory JSON cache.
         *
         * @param maxSize Maximum number of entries.
         * @param ttlMs Time-to-live in milliseconds.
         */
        fun create(
            maxSize: Int = 100,
            ttlMs: Long = 3_600_000L
        ): WordApiCache {
            return InMemoryWordApiCache(InMemoryCache(maxSize, ttlMs))
        }
    }
}
