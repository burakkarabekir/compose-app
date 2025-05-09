package com.bksd.feature_home.domain.usecase

import com.bksd.core_domain.model.WordDetail
import com.bksd.core_domain.repository.WordRepository
import com.bksd.core_domain.result.DomainResult
import com.bksd.core_domain.usecase.FlowUseCase
import kotlinx.coroutines.flow.Flow

class GetRecentWordsUseCase(
    private val repository: WordRepository
) : FlowUseCase<Unit, DomainResult<List<WordDetail>>>() {

    override suspend fun invoke(params: Unit): Flow<DomainResult<List<WordDetail>>> =
        repository.getRecentWords()
}