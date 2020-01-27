package com.jtouzy.demo.network

import com.jtouzy.demo.network.dto.CharacterMarvelDto
import kotlinx.serialization.Serializable

@Serializable
data class MarvelCharacter(
    val name: String,
    val imageUrl: String,
    val description: String
) {

    constructor(dto: CharacterMarvelDto) : this(
        name = dto.name,
        imageUrl = dto.imageUrl,
        description = dto.description
    )
}
