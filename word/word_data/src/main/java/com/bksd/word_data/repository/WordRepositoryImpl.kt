package com.bksd.core_data.repository

import android.util.Log
import com.bksd.core_domain.exception.WordApiException
import com.bksd.core_domain.exception.WordNotFoundException
import com.bksd.core_domain.mapper.BaseMapper
import com.bksd.core_domain.mapper.ListMapper
import com.bksd.core_domain.result.DomainResult
import com.bksd.word_data.local.datasource.WordLocalDataSource
import com.bksd.word_data.local.entity.WordEntity
import com.bksd.word_data.remote.datasource.WordRemoteDataSource
import com.bksd.word_data.remote.dto.WordDto
import com.bksd.word_domain.model.WordDetail
import com.bksd.word_domain.model.WordOfDay
import com.bksd.word_domain.repository.WordRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow

/**
 * Implementation of WordRepository that uses WordApiService
 * to fetch word data and maps it to domain models.
 *
 * @property wordApiService The service used to access the Word API
 */
class WordRepositoryImpl(
    private val remoteDataSource: WordRemoteDataSource,
    private val localeDataSource: WordLocalDataSource,
    private val mapper: BaseMapper<WordDto, WordOfDay>,
    private val recentWordsMapper: ListMapper<WordEntity, WordDetail>,
    private val wordEntityToModelMapper: BaseMapper<WordEntity, WordDetail>,
    private val wordDtoToEntityMapper: BaseMapper<WordDto, WordEntity>,
    private val wordMapper: BaseMapper<WordDto, WordDetail>,
) : WordRepository {

    override suspend fun fetchWord(
         word: String
    ): Flow<DomainResult<WordDetail>> = flow {
        emit(DomainResult.Loading)
        localeDataSource.getWordByName(word)?.let {
            emit(DomainResult.Success(wordEntityToModelMapper.map(it)))
            Log.d("ComposeAppLogger", "fetchWord :: cache :: $word")
            return@flow
        }
        val response = remoteDataSource.fetchWord(word)

        response.getOrNull()?.let {
            val result: WordDetail = try {
                wordMapper.map(it)
            } catch (e: Exception) {
                emit(DomainResult.Error(e, e.message.orEmpty()))
                return@flow
            }
            localeDataSource.saveWord(wordDtoToEntityMapper.map(it))
            emit(DomainResult.Success(result))
        } ?: emit(DomainResult.Empty)
    }

    override suspend fun setWordFavorite(word: String, isFavorite: Boolean) {
        localeDataSource.updateFavoriteStatus(word, isFavorite)
    }

    override suspend fun getWordOfDay(): Flow<DomainResult<WordOfDay>> = flow {
        emit(DomainResult.Loading)
        val response = remoteDataSource.fetchWordOfDay()

        response.getOrNull()?.let {
            val result: WordOfDay = try {
                mapper.map(it)
            } catch (e: Exception) {
                emit(DomainResult.Error(e, e.message.orEmpty()))
                return@flow
            }
            localeDataSource.saveWord(wordDtoToEntityMapper.map(it))
            emit(DomainResult.Success(result))
        } ?: emit(DomainResult.Empty)
    }

    override suspend fun getWordDetail(word: String): Flow<DomainResult<WordDetail>> = flow {
        emit(DomainResult.Loading)
        val cachedWordEntity = localeDataSource.getWordByName(word)

        cachedWordEntity?.let {
            delay(200)
            emit(DomainResult.Success(wordEntityToModelMapper.map(it)))
            Log.d("ComposeAppLogger", "getWordDetail :: cache :: $word")
            return@flow
        }
        fetchWord(word).collect()
    }

    override suspend fun getRecentWords(): Flow<DomainResult<List<WordDetail>>> = flow {
        emit(DomainResult.Loading)
        val recentWordList = localeDataSource.getRecentWords()

        recentWordList?.firstOrNull()?.let {
            val result: List<WordDetail> = try {
                recentWordsMapper.map(it)
            } catch (e: Exception) {
                emit(DomainResult.Error(e, e.message.orEmpty()))
                return@flow
            }
            emit(DomainResult.Success(result))
        }
    }

    /* override suspend fun getWordOfDay(): Flow<DomainResult<WordOfDayModel>> = wordApiService.getWordOfDay()
         .map { response ->  DomainResult.Success(
             WordOfDayModel(
                 word = response.word.orEmpty(),
                 pronunciation = response.pronunciation?.all.orEmpty(),
                 definition = response.results?.firstOrNull()?.definition.orEmpty(),
             )
             )
         }
         .catch { throwable -> throw mapToRepositoryException(throwable = throwable, word = null) }
 */
    /*override suspend fun getWordDefinition(word: String): Flow<WordServiceResponse> {
        return wordApiService.getWordDefinition(word)
            .map { response ->
                WordServiceResponse(
                    word = response.word,
                    definitions = response.results.map { result ->
                        mapToDefinition(result)
                    }
                )
            }
            .catch { throwable ->
                throw mapToRepositoryException(throwable, word)
            }
    }

    override suspend fun getWordSynonyms(word: String): Flow<WordSynonyms> {
        return wordApiService.getWordSynonyms(word)
            .map { response ->
                WordSynonyms(
                    word = response.word,
                    synonyms = response.results.flatMap { it.synonyms ?: emptyList() }.distinct()
                )
            }
            .catch { throwable ->
                throw mapToRepositoryException(throwable, word)
            }
    }

    override suspend fun getWordPronunciation(word: String): Flow<WordPronunciation> {
        return wordApiService.getWordPronunciation(word)
            .map { response ->
                WordPronunciation(
                    word = response.word,
                    pronunciation = response.pronunciation?.all
                )
            }
            .catch { throwable ->
                throw mapToRepositoryException(throwable, word)
            }
    }

    override suspend fun wordExists(word: String): Flow<Boolean> {
        return try {
            wordApiService.getWordInformation(word)
                .map { true }
                .catch { throwable ->
                    if (throwable is WordNotFoundException) {
                        emit(false)
                    } else {
                        throw mapToRepositoryException(throwable, word)
                    }
                }
        } catch (e: Exception) {
            throw mapToRepositoryException(e, word)
        }
    }

    */
    /**
     * Maps a Result DTO to a Definition domain model.
     *//*
    private fun mapToDefinition(result: com.example.core.data.dto.Result): Definition {
        return Definition(
            text = result.definition,
            partOfSpeech = result.partOfSpeech,
            synonyms = result.synonyms ?: emptyList(),
            related = RelatedWords(
                typeOf = result.typeOf ?: emptyList(),
                memberOf = result.memberOf ?: emptyList(),
                hasTypes = result.hasTypes ?: emptyList(),
                partOf = result.partOf ?: emptyList()
            )
        )
    }*/

    /**
     * Maps API exceptions to repository exceptions.
     */
    private fun mapToRepositoryException(throwable: Throwable, word: String?): Exception =
        when (throwable) {
            is WordNotFoundException -> WordNotInRepositoryException(word.orEmpty())
            is WordApiException -> WordRepositoryException(
                "API error while processing word '$word'",
                throwable
            )

            else -> WordRepositoryException(
                "Unexpected error while processing word '$word'",
                throwable
            )
        }
}

/**
 * Exception thrown when a word is not found in the repository.
 */
class WordNotInRepositoryException(word: String) :
    Exception("Word not found in repository: '$word'")

/**
 * General exception for repository-related errors.
 */
class WordRepositoryException(
    message: String,
    cause: Throwable? = null
) : Exception(message, cause)