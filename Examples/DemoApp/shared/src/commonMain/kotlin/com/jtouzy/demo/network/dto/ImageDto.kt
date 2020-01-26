package com.jtouzy.demo.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class ImageDto(
    val path: String,
    val extension: String,
    val completeImagePath: String
)
