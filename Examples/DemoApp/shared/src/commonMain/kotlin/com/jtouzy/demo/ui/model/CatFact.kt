package com.jtouzy.demo.ui.model

import com.jtouzy.demo.network.dto.Data

data class CatFact(
    val id: String,
    val text: String
) {

    constructor(data: Data) : this(
        data.id,
        data.text
    )
}
