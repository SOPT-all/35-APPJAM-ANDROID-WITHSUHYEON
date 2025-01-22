package com.sopt.withsuhyeon.domain.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PostItemModel(
    @SerialName("postId")
    val postId: Long,
    @SerialName("title")
    val title: String,
    @SerialName("price")
    val price: Int,
    @SerialName("gender")
    val gender: Boolean,
    @SerialName("age")
    val age: String,
    @SerialName("date")
    val date: String,
    @SerialName("matching")
    val matching: Boolean
)