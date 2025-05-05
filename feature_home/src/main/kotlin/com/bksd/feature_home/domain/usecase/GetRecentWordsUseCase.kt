package com.bksd.feature_home.domain.usecase

import com.bksd.core_domain.model.WordInformation
import com.bksd.core_domain.repository.WordRepository
import com.bksd.core_domain.result.DomainResult
import com.bksd.core_domain.usecase.FlowUseCase
import kotlinx.coroutines.flow.Flow

class GetRecentWordsUseCase(
    private val repository: WordRepository
) : FlowUseCase<Unit, DomainResult<List<WordInformation>>>() {

    override suspend fun invoke(params: Unit): Flow<DomainResult<List<WordInformation>>> =
        repository.getRecentWords()
}