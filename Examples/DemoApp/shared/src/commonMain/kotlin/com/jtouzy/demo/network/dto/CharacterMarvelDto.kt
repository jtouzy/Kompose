package com.jtouzy.demo.network.dto

import kotlinx.serialization.Serializable

@Serializable
class CharacterMarvelDto {

    lateinit var name: String
    lateinit var description: String
    lateinit var thumbnail: ImageDto
    var comics: ListWrapper<OccurrenceDto> = ListWrapper()
    var series: ListWrapper<OccurrenceDto> = ListWrapper()
    var stories: ListWrapper<OccurrenceDto> = ListWrapper()
    var events: ListWrapper<OccurrenceDto> = ListWrapper()

    val imageUrl: String
        get() = thumbnail.completeImagePath
}