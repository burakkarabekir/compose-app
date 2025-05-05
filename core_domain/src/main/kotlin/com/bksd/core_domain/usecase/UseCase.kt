package com.bksd.core_domain.usecase

import com.bksd.core_domain.result.DomainResult
import kotlinx.coroutines.flow.Flow

abstract class UseCase<in P, R> {
    abstract suspend operator fun invoke(parameters: P): Result<R>
}

abstract class NoParamUseCase<R> {
    abstract suspend operator fun invoke(): Result<R>
}

abstract class FlowUseCase<in P, R> {
    abstract suspend operator fun invoke(params: P): Flow<DomainResult<*>>
}

abstract class NoParamFlowUseCase<R> {
    abstract operator fun invoke(): Flow<Result<R>>
}