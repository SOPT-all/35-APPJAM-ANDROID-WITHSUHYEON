package com.sopt.withsuhyeon.domain.entity

import kotlinx.serialization.SerialName

data class GalleryPostDetailModel(
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