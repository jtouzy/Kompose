package com.jtouzy.demo.network

import com.jtouzy.demo.BuildKonfig
import com.jtouzy.demo.network.dto.CharacterMarvelDto
import com.jtouzy.demo.network.dto.DataWrapper
import com.jtouzy.demo.utils.getTimeStamp
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
import kotlinx.serialization.list

class MarvelApi {

    private val client = HttpClient {
        install(JsonFeature) {
            serializer = KotlinxSerializer()
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
        val dataWrapper = Json.parse(DataWrapper.serializer(typeSerial0 = CharacterMarvelDto.serializer().list), this)
        dataWrapper.data?.results?.map { MarvelCharacter(it) } ?: error("No data")
    }

    private fun HttpRequestBuilder.addCredentialsParameters() {
        val timeStamp = getTimeStamp()
        val md5 =
            (timeStamp.toString() + BuildKonfig.PRIVATE_KEY + BuildKonfig.PUBLIC_KEY).toByteArray().md5().toString()
        parameter("apikey", BuildKonfig.PUBLIC_KEY)
        parameter("hash", "0" * (32 - md5.length) + md5)
        parameter("ts", "$timeStamp")
    }

    companion object {
        private const val BASE_URL = "gateway.marvel.com/v1/public"
    }
}

private operator fun String.times(i: Int) = (1..i).fold("") { acc, _ -> acc + this }