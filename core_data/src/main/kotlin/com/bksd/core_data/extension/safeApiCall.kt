package com.bksd.core_data.extension

import com.bksd.core_data.model.NetworkResponse
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException

suspend fun <T> safeApiCall(
    apiCall: suspend () -> T
): NetworkResponse<T> = try {
    NetworkResponse.Success(apiCall())
} catch (e: ClientRequestException) {
    NetworkResponse.Error(e.response.status.value, e.message)
} catch (e: ServerResponseException) {
    NetworkResponse.Error(e.response.status.value, e.message)
} catch (e: Exception) {
    NetworkResponse.Error(message = e.message)
}