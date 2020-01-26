package com.jtouzy.demo.network

import com.jtouzy.demo.network.dto.Container
import com.jtouzy.demo.ui.model.CatFact
import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.get
import kotlinx.serialization.json.Json

class CatApi {

    private val client = HttpClient {
        install(JsonFeature) {
            serializer = KotlinxSerializer(Json.nonstrict)
        }
    }

    suspend fun getFacts(): List<CatFact> = client.get<String> {
        url {
            host = BASE_URL
            encodedPath = "facts"
        }
    }.run {
        val container = Json.parse(Container.serializer(), this)
        container.all.map { CatFact(it) }
    }

    companion object {
        private const val BASE_URL = "cat-fact.herokuapp.com"
    }
}