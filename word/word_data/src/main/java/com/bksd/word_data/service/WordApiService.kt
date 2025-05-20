package com.bksd.word_data.service

import com.bksd.word_data.remote.dto.WordDto

interface WordApiService {
    suspend fun fetchWord(word: String): WordDto
    suspend fun fetchWordOfDay(): WordDto
    suspend fun getWordDefinition(word: String): WordDto
    suspend fun getWordSynonyms(word: String): WordDto
    suspend fun getWordPronunciation(word: String): WordDto
    fun clearCache()
}