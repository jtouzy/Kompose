package com.jtouzy.demo.network

import com.jtouzy.demo.network.dto.CharacterDto
import com.jtouzy.demo.ui.model.Character
import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.get
import io.ktor.http.URLProtocol
import io.ktor.http.headersOf
import kotlinx.serialization.json.Json
import kotlinx.serialization.list

class BreakingBadApi {

    private val client = HttpClient {
        install(JsonFeature) {
            serializer = KotlinxSerializer()
        }
        headersOf("Accept", "application/json")
        headersOf("Content-Type", "application/json")
    }

    suspend fun getCharacters(): List<Character> = client.get<String> {
        url {
            host = BASE_URL
            protocol = URLProtocol.HTTPS
            encodedPath = "characters"
        }
    }.run {
        Json.parse(CharacterDto.serializer().list, this).map { Character(it) }
    }

    companion object {
        private const val BASE_URL = "breakingbadapi.com/api"
    }
}
