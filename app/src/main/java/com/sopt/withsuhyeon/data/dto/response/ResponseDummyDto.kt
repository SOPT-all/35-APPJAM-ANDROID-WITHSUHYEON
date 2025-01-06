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

@Serializable
data class ResponseUserDto(
    @SerialName("data")
    val data: UserDto,
    @SerialName("support")
    val support: SupportDto
)

@Serializable
data class UserDto(
    @SerialName("id")
    val id: Int,
    @SerialName("email")
    val email: String,
    @SerialName("first_name")
    val firstName: String,
    @SerialName("last_name")
    val lastName: String,
    @SerialName("avatar")
    val avatar: String
)

@Serializable
data class SupportDto(
    @SerialName("url")
    val url: String,
    @SerialName("text")
    val text: String
)
