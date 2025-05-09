package com.bksd.core_data.datasource

import com.bksd.core_data.api.cache.InMemoryWordApiCache
import com.bksd.core_data.api.cache.WordApiCache
import com.bksd.core_data.config.JsonProvider
import com.bksd.core_data.dto.WordDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WordLocalDataSourceImpl(
    private val cache: WordApiCache,
) : WordLocalDataSource {
    /**
     * Returns a list of all cached WordServiceResponse objects.
     */
    override suspend fun getCachedWords(): List<WordDto>? =
        withContext(Dispatchers.IO) {
            if (cache is InMemoryWordApiCache) {
                cache.getAll { raw ->
                    JsonProvider.instance.decodeFromString(WordDto.serializer(), raw)
                }
            } else {
                emptyList()
            }
        }
}