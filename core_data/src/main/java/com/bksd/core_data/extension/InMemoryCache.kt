package com.bksd.core_data.extension

import java.util.concurrent.ConcurrentHashMap

/**
 * A thread-safe in-memory cache implementation with time-to-live functionality.
 *
 * @param K The type of keys in the cache
 * @param V The type of values in the cache
 * @property maxSize Maximum number of entries in the cache
 * @property ttlMs Time-to-live in milliseconds for cache entries
 */
class InMemoryCache<K, V>(
    private val maxSize: Int,
    private val ttlMs: Long
) {
    private data class CacheEntry<V>(
        val value: V,
        val timestamp: Long
    )

    // Thread-safe map implementation
    private val cache = ConcurrentHashMap<K, CacheEntry<V>>()

    /**
     * Retrieves a value from the cache if it exists and hasn't expired.
     *
     * @param key The key to look up
     * @return The cached value or null if not found or expired
     */
    fun get(key: K): V? {
        val entry = cache[key] ?: return null
        val currentTime = System.currentTimeMillis()

        // Check if entry has expired
        if (currentTime - entry.timestamp > ttlMs) {
            cache.remove(key)
            return null
        }

        return entry.value
    }

    /**
     * Stores a value in the cache with the current timestamp.
     *
     * @param key The key to store
     * @param value The value to cache
     */
    fun put(key: K, value: V) {
        // Ensure we don't exceed maximum size
        evictIfNeeded()

        cache[key] = CacheEntry(
            value = value,
            timestamp = System.currentTimeMillis()
        )
    }

    /**
     * Removes an entry from the cache.
     *
     * @param key The key to remove
     * @return True if an entry was removed, false otherwise
     */
    fun remove(key: K): Boolean {
        return cache.remove(key) != null
    }

    /**
     * Clears all entries from the cache.
     */
    fun clear() {
        cache.clear()
    }

    /**
     * Gets the current size of the cache.
     *
     * @return The number of entries in the cache
     */
    fun size(): Int = cache.size

    /**
     * Checks if the cache contains a non-expired entry for the given key.
     *
     * @param key The key to check
     * @return True if the cache contains a non-expired entry for the key
     */
    fun contains(key: K): Boolean {
        return get(key) != null
    }

    /**
     * Evicts entries if the cache size exceeds the maximum size.
     * Removes expired entries first, then oldest entries if still needed.
     */
    private fun evictIfNeeded() {
        // First, remove all expired entries
        removeExpiredEntries()

        // If still over capacity, remove oldest entries
        if (cache.size >= maxSize) {
            val entries = cache.entries.sortedBy { it.value.timestamp }
            val entriesToRemove = entries.take(entries.size - maxSize + 1)

            for ((key, _) in entriesToRemove) {
                cache.remove(key)
            }
        }
    }

    /**
     * Removes all expired entries from the cache.
     */
    private fun removeExpiredEntries() {
        val currentTime = System.currentTimeMillis()
        val iterator = cache.entries.iterator()

        while (iterator.hasNext()) {
            val entry = iterator.next()
            if (currentTime - entry.value.timestamp > ttlMs) {
                iterator.remove()
            }
        }
    }
}
