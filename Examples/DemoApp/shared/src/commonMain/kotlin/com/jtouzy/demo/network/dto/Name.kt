package com.jtouzy.demo.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class Name(
    val first: String,
    val last: String
)
