package com.jtouzy.demo.network

import com.jtouzy.demo.BuildKonfig
import com.jtouzy.demo.network.dto.DataWrapper
import com.soywiz.klock.DateTime
import com.soywiz.krypto.md5
import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.headersOf
import kotlinx.io.core.toByteArray
import kotlinx.serialization.json.Json

class MarvelApi {

    private val timestamp: String
        get() {
            return (DateTime.now().unixMillisLong / 1000L).toString()
        }

    private val client = HttpClient {
        install(JsonFeature) {
            serializer = KotlinxSerializer(Json.nonstrict)
        }
        headersOf("Accept", "application/json")
        headersOf("Accept-Language", "en")
        headersOf("Content-Type", "application/json")
    }

    suspend fun getCharacters(): List<MarvelCharacter> = client.get<String> {
        url {
            host = BASE_URL
            encodedPath = "characters"
            parameter("limit", 20)
            addCredentialsParameters()
        }
    }.run {
        val dataWrapper = Json.parse(DataWrapper.serializer(), this)
        dataWrapper.data.results.map { MarvelCharacter(it) }
    }

    private fun HttpRequestBuilder.addCredentialsParameters() {
        val timeStamp = timestamp
        parameter("apikey", BuildKonfig.PUBLIC_KEY)
        parameter("hash", generateHash(timeStamp))
        parameter("ts", timeStamp)
    }

    @UseExperimental(ExperimentalUnsignedTypes::class)
    private fun generateHash(timestamp: String): String =
        (timestamp + BuildKonfig.PRIVATE_KEY + BuildKonfig.PUBLIC_KEY).toByteArray().md5().asUByteArray()
            .joinToString("") { it.toString(16).padStart(2, '0') }

    companion object {
        private const val BASE_URL = "gateway.marvel.com/v1/public"
    }
}
