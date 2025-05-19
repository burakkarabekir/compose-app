package com.bksd.core_ui.extension

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun ViewModel.simpleLaunch(
    onError: (Throwable) -> Unit = { it.printStackTrace() },
    block: suspend CoroutineScope.() -> Unit
) {
    viewModelScope.launch {
        try {
            block()
        } catch (e: Exception) {
            onError(e)
        }
    }
}