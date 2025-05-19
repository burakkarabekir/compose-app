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
        val rawText = try {
            response.bodyAsText()
        } catch (e: Exception) {
            throw WordApiParsingException(
                "Unable to read response body: ${e.message} :: ${T::class.simpleName}",
                e
            )
        }
        if (!response.status.isSuccess()) {
            throw WordApiNetworkException(
                "${response.status.value} ${response.status.description}",
                Throwable(message = response.status.description)
            )
        }

        Log.d("ComposeAppLogger", "ResponseMapper :: Response body :: $rawText")
        Log.d("ComposeAppLogger", "ResponseMapper :: Expected type :: ${T::class.simpleName}")

        // Ktor’s built-in deserializer which works with your ContentNegotiation setup.
        return try {
            JsonProvider.instance.decodeFromString<T>(rawText)
        } catch (e: Exception) {
            throw WordApiParsingException(
                "Failed to parse response body to ${T::class.simpleName} $rawText",
                e
            )
        }
    }
}
