package com.jtouzy.demo.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class DataContainer(
    var results: List<CharacterMarvelDto>
)
