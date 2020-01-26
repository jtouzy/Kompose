package com.jtouzy.demo.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Data(
	@SerialName("_id") val id: String,
	val text: String,
	val type: String? = null,
	val user: User? = null,
	val upvotes: Int? = null,
	val userUpvoted: String? = null
)
