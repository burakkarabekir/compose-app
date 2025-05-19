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

    private val cache = ConcurrentHashMap<K, CacheEntry<V>>()

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

    fun put(key: K, value: V) {
        // Ensure we don't exceed maximum size
        evictIfNeeded()

        cache[key] = CacheEntry(
            value = value,
            timestamp = System.currentTimeMillis()
        )
    }

    fun remove(key: K): Boolean {
        return cache.remove(key) != null
    }

    fun clear() {
        cache.clear()
    }
    fun size(): Int = cache.size

    fun contains(key: K): Boolean {
        return get(key) != null
    }

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

    fun snapshot(): Map<K, V> = cache
        .filterValues { System.currentTimeMillis() - it.timestamp <= ttlMs }
        .mapValues { it.value.value }
}
