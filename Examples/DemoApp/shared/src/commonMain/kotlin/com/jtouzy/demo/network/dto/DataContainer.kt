package com.jtouzy.demo.network.dto

import kotlinx.serialization.Serializable

@Serializable
class DataContainer<T> {
    var results: T? = null
}