package com.bksd.core_data.config

import kotlinx.serialization.json.Json

object JsonProvider {
    val instance: Json = Json {
        prettyPrint = true
        isLenient = true
        ignoreUnknownKeys = true
    }
}