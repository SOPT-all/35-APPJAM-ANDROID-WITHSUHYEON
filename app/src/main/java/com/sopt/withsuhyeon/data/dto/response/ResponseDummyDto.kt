package com.sopt.withsuhyeon.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseDummyDto(
    @SerialName("reviews")
    val reviews: List<ReviewItem>
)

@Serializable
data class ReviewItem(
    @SerialName("userName")
    val userName: String,
    @SerialName("content")
    val content: String,
    @SerialName("score")
    val score: Float,
    @SerialName("isAdd")
    val isAdd: Boolean,
    @SerialName("image1")
    val image1: String? = null,
    @SerialName("image2")
    val image2: String? = null,
    @SerialName("image3")
    val image3: String? = null,
    @SerialName("createdAt")
    val createdAt: String
)
