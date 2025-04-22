package com.bksd.core_data.api.mapper

import com.bksd.core_domain.exception.WordApiParsingException
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText

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
        return try {
            response.body<T>()
        } catch (e: Exception) {
            throw WordApiParsingException(
                "Failed to parse response body to ${T::class.simpleName}",
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
    suspend fun getResponseText(response: HttpResponse): String {
        return try {
            response.bodyAsText()
        } catch (e: Exception) {
            "Unable to read response body: ${e.message}"
        }
    }
}
