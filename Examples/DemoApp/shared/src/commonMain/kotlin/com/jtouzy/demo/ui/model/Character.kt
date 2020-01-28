package com.jtouzy.demo.ui.model

import com.jtouzy.demo.network.dto.CharacterDto

data class Character(
    val name: String,
    val nickname: String,
    val imageUrl: String
) {
    constructor(dto: CharacterDto) : this(
        dto.name,
        dto.nickname,
        dto.img
    )
}
