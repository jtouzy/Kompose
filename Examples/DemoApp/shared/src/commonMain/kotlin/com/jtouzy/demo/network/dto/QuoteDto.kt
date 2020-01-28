package com.jtouzy.demo.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class QuoteDto(
    val quote_id : Int,
    val quote : String,
    val author : String,
    val series : String
)
