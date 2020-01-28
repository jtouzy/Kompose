package com.jtouzy.demo.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QuoteDto(
    @SerialName("quote_id") val id: Int,
    val quote: String,
    val author: String,
    val series: String
)
