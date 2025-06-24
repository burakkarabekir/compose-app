package com.bksd.core_ui.extension

import com.bksd.core_domain.mapper.BaseMapper
import com.bksd.core_domain.result.DomainResult
import com.bksd.core_ui.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Converts a DomainResult to a UiState.
 * This is typically used when crossing the boundary from domain layer to UI layer.
 */
private fun <T : Any, U : Any> DomainResult<T>.toUiState(map: (T) -> U): UiState<U> = when (this) {
    is DomainResult.Loading -> loadingUiState()
    is DomainResult.Empty -> emptyUiState()
    is DomainResult.Success -> successUiState(map(data))
    is DomainResult.Error -> errorUiState(message)
}

fun <T : Any, U : Any> DomainResult<T>.toUiState(mapper: BaseMapper<T, U>): UiState<U> =
    toUiState { mapper.map(it) }

/**
 * Maps a Flow of DomainResult to a Flow of UiState.
 * This is typically used when crossing the boundary from domain layer to UI layer.
 */
fun <T : Any, R : Any> Flow<DomainResult<T>>.toUiState(mapper: BaseMapper<T, R>): Flow<UiState<R>> =
    map { result ->
        when (result) {
            is DomainResult.Loading -> UiState.Loading
            is DomainResult.Success -> UiState.Success(mapper.map(result.data))
            is DomainResult.Error -> UiState.Error(result.message)
            DomainResult.Empty -> UiState.Empty
        }
    }

/**
 * Create an initial UiState with the provided data.
 */
fun <T> initialUiState(): UiState<T> = UiState.Initial

/**
 * Create a empty UiState.
 */
fun <T> emptyUiState(): UiState<T> = UiState.Empty
/**
 * Create a loading UiState.
 */
fun <T> loadingUiState(): UiState<T> = UiState.Loading

/**
 * Create a success UiState with the provided data.
 */
fun <T> successUiState(data: T): UiState<T> = UiState.Success(data)

/**
 * Create an error UiState with the provided error message.
 * @param message The error message to be included in the error state
 */
fun <T> errorUiState(message: String): UiState<T> = UiState.Error(message)