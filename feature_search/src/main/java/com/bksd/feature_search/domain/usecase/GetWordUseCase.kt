package com.bksd.feature_search.domain.usecase

import com.bksd.core_domain.model.WordDetailModel
import com.bksd.core_domain.repository.WordRepository
import com.bksd.core_domain.result.DomainResult
import com.bksd.core_domain.usecase.FlowUseCase
import kotlinx.coroutines.flow.Flow

class GetWordUseCase(
    private val repository: WordRepository
) : FlowUseCase<GetWordUseCase.Params, DomainResult<WordDetailModel>>() {

    data class Params(val word: String)

    override suspend fun invoke(params: Params): Flow<DomainResult<WordDetailModel>> =
        repository.getWordInformation(params.word)
}