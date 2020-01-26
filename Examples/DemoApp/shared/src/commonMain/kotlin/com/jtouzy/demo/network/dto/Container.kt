package com.jtouzy.demo.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class Container(
    val all: List<Data>
)
