package com.jtouzy.demo.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class CharacterMarvelDto(
    val name: String,
    val description: String,
    val thumbnail: ImageDto,
    val imageUrl: String
)
