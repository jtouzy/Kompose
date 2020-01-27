package com.jtouzy.demo.ui.model

import com.jtouzy.demo.network.dto.CharacterDto

data class Character(
    val name: String,
    val nickname: String
) {
    constructor(dto: CharacterDto) : this(
        dto.name,
        dto.nickname
    )
}
