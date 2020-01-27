package com.jtouzy.demo.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class DataWrapper(
    val data: DataContainer
)
