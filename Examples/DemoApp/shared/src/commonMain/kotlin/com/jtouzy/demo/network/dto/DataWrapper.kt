package com.jtouzy.demo.network.dto

import kotlinx.serialization.Serializable

@Serializable
class DataWrapper<T> {
    var data: DataContainer<T>? = null
}