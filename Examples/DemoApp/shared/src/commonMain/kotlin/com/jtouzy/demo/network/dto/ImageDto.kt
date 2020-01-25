package com.jtouzy.demo.network.dto

import kotlinx.serialization.Serializable

@Serializable
class ImageDto {

    lateinit var path: String
    lateinit var extension: String

    val completeImagePath: String
        get() = "$path.$extension"
}