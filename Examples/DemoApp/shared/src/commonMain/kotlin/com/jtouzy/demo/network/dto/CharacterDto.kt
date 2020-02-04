package com.jtouzy.demo.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class CharacterDto(
    val name: String,
    val img: String
)
