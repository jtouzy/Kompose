package com.jtouzy.demo.ui.model

import com.jtouzy.demo.network.dto.Data

data class CatFact(
    val id: String,
    val user: String,
    val fact: String
) {

    constructor(data: Data) : this(
        data.id,
        data.user?.name?.let { "${it.first} ${it.last}" } ?: "Unknown author",
        data.text
    )
}
