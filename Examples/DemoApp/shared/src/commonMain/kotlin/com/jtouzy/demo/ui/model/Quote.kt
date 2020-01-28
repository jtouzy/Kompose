package com.jtouzy.demo.ui.model

import com.jtouzy.demo.network.dto.QuoteDto

data class Quote(
    val quote: String,
    val series: String
) {
    constructor(dto: QuoteDto) : this(
        dto.quote,
        dto.series
    )
}
