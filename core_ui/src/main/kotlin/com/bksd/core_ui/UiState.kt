package com.bksd.core_ui

sealed interface UiState<out T> {
    data object Initial : UiState<Nothing>
    data object Loading : UiState<Nothing>
    data object Empty : UiState<Nothing>
    data class Success<T>(val data: T) : UiState<T>
    data class Error(val message: String) : UiState<Nothing>

    val isInitial: Boolean get() = this is Initial
    val isLoading: Boolean get() = this is Loading
    val isSuccess: Boolean get() = this is Success
    val isError: Boolean get() = this is Error

    /**
     * Gets the data if this state is a success, otherwise returns null.
     */
    fun getOrNull(): T? = when (this) {
        is Success -> data
        else -> null
    }

    /**
     * Gets the data if this state is a success
     */
    fun get(): T = (this as Success).data

    fun <R> map(transform: (T) -> R): UiState<R> {
        return when (this) {
            is Initial -> Initial
            is Loading -> Loading
            is Success -> Success(transform(data))
            is Error -> Error(message)
            Empty -> Empty
        }
    }
}