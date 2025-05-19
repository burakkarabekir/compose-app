package com.bksd.feature_home.domain.usecase

import com.bksd.core_domain.result.DomainResult
import com.bksd.core_domain.usecase.FlowUseCase
import com.bksd.word_domain.model.WordDetail
import com.bksd.word_domain.repository.WordRepository
import kotlinx.coroutines.flow.Flow

class GetRecentWordsUseCase(
    private val repository: WordRepository
) : FlowUseCase<Unit, DomainResult<List<WordDetail>>>() {

    override suspend fun invoke(params: Unit): Flow<DomainResult<List<WordDetail>>> =
        repository.getRecentWords()
}