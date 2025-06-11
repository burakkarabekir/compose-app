package com.bksd.core_data.extension

import com.bksd.core_data.model.NetworkResult
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException

suspend fun <T> safeApiCall(
    apiCall: suspend () -> T
): NetworkResult<T> = try {
    NetworkResult.Success(apiCall())
} catch (e: ClientRequestException) {
    NetworkResult.Error(e.response.status.value, e.message)
} catch (e: ServerResponseException) {
    NetworkResult.Error(e.response.status.value, e.message)
} catch (e: Exception) {
    NetworkResult.Error(message = e.message)
}