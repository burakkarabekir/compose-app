package com.bksd.feature_home.domain.usecase

import com.bksd.core_domain.result.DomainResult
import com.bksd.core_domain.usecase.FlowUseCase
import com.bksd.word_domain.model.WordDetail
import com.bksd.word_domain.repository.WordRepository
import kotlinx.coroutines.flow.Flow

class GetWordDetailUseCase(
    private val repository: WordRepository
) : FlowUseCase<String, DomainResult<WordDetail>>() {
    override suspend operator fun invoke(params: String): Flow<DomainResult<WordDetail>> =
        repository.getWordDetail(params)
}