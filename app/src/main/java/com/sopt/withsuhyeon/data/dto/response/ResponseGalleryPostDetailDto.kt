package com.sopt.withsuhyeon.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseGalleryPostDetailDto(
    @SerialName("imageUrl")
    val imageUrl: String,
    @SerialName("category")
    val category: String,
    @SerialName("title")
    val title: String,
    @SerialName("profileImage")
    val profileImage: String,
    @SerialName("nickname")
    val nickname: String,
    @SerialName("createdAt")
    val createdAt: String,
    @SerialName("content")
    val content: String,
    @SerialName("owner")
    val owner: Boolean
)
