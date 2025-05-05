package com.bksd.core_data.api.mapper

import android.util.Log
import com.bksd.core_data.config.JsonProvider
import com.bksd.core_domain.exception.WordApiNetworkException
import com.bksd.core_domain.exception.WordApiParsingException
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.isSuccess

/**
 * Component responsible for mapping HTTP responses to domain objects.
 * Handles parsing and error handling related to response transformations.
 */
class ResponseMapper {
    /**
     * Maps an HTTP response to the specified type.
     *
     * @param T The type to map the response to
     * @param response The HTTP response to map
     * @return The mapped object
     * @throws WordApiParsingException if parsing fails
     */
    suspend inline fun <reified T> mapResponse(response: HttpResponse): T {
        // Read raw text for both success and error cases
        val rawText = getResponseText(response)
        Log.d("ComposeAppLogger :: ResponseMapper", "mapResponse: $rawText")
        // Handle non-2xx HTTP statuses
        if (!response.status.isSuccess()) {
            throw WordApiNetworkException(
                "HTTP ${response.status.value} error: $rawText",
                Throwable(message = response.status.description)
            )
        }

        // Parse JSON into the desired type
        return try {
            JsonProvider.instance.decodeFromString(rawText)
        } catch (e: Exception) {
            throw WordApiParsingException(
                "Failed to parse response body to ${T::class.simpleName} $rawText",
                e
            )
        }
    }

    /**
     * Gets the response body as text for debugging or error reporting.
     *
     * @param response The HTTP response
     * @return The response body as text
     */
    suspend fun getResponseText(response: HttpResponse): String = try {
        response.bodyAsText()
    } catch (e: Exception) {
        "WordApiParsingException :: Unable to read response body: ${e.message}"
    }
}
