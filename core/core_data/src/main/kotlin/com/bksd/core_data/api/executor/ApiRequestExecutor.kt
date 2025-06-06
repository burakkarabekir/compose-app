package com.bksd.core_data.api.executor

import com.bksd.core_data.BuildConfig
import com.bksd.core_data.config.JsonProvider
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.parameter
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json

/**
 * Component responsible for executing HTTP requests to the Word API.
 * Handles the low-level details of making requests with appropriate headers and parameters.
 */
class ApiRequestExecutor(
    private val config: WordApiConfig
) {

    val client: HttpClient by lazy {
        HttpClient(Android) {
            install(ContentNegotiation) {
                json(JsonProvider.instance)
            }

            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        println("Ktor: $message")
                    }
                }
                level = if (config.isDebug) LogLevel.ALL else LogLevel.NONE
            }

            engine {
                connectTimeout = config.timeoutMs.toInt()
                socketTimeout = config.timeoutMs.toInt()
            }
            expectSuccess = true
        }
    }

    /**
     * Executes a request to the specified endpoint for the given word.
     *
     * @param endpoint The API endpoint to call
     * @param word The word to look up
     * @param params Optional additional parameters
     * @return The HTTP response
     */
    suspend fun execute(
        endpoint: String,
        pathParam: String?,
        queryParams: Map<String, String> = emptyMap()
    ): HttpResponse {
        buildString {
            append(config.baseUrl).append("/").append(endpoint)
            pathParam?.takeIf(String::isNotBlank)?.let { append("/").append(it) }
        }.trim().lowercase().let { url ->
            return client.get(url) {
            headers {
                append(BuildConfig.KEY_API_KEY, config.apiKey)
                append(BuildConfig.KEY_API_HOST, BuildConfig.VALUE_API_HOST)
            }
            contentType(ContentType.Application.Json)
                queryParams.forEach { (key, value) -> parameter(key, value) }
            }
        }
    }

    companion object {
        // Common endpoint constants
        const val ENDPOINT_WORD = "words"
        const val ENDPOINT_DEFINITION = "definitions"
        const val ENDPOINT_SYNONYMS = "synonyms"
        const val ENDPOINT_PRONUNCIATION = "pronunciation"
    }
}