package com.jtouzy.demo.network

import com.jtouzy.demo.network.dto.CharacterDto
import com.jtouzy.demo.network.dto.QuoteDto
import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.DEFAULT
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.ParametersBuilder
import io.ktor.http.URLProtocol
import io.ktor.http.headersOf
import kotlinx.serialization.json.Json
import kotlinx.serialization.list

class BreakingBadApi {

    private val client = HttpClient {
        install(JsonFeature) {
            serializer = KotlinxSerializer()
        }
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.INFO
        }
        headersOf("Accept", "application/json")
        headersOf("Content-Type", "application/json")
    }

    suspend fun getCharacters(): List<CharacterDto> = client.get<String> {
        url {
            host = BASE_URL
            protocol = URLProtocol.HTTPS
            encodedPath = "characters"
        }
    }.run {
        Json.parse(CharacterDto.serializer().list, this)
    }

    suspend fun getCharacterQuotes(name: String): List<QuoteDto> = client.get<String> {
        url {
            host = BASE_URL
            protocol = URLProtocol.HTTPS
            encodedPath = "quote"
            parameter("author", name)
        }
    }.run {
        Json.parse(QuoteDto.serializer().list, this)
    }

    companion object {
        private const val BASE_URL = "breakingbadapi.com/api"
    }
}
