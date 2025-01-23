package com.sopt.withsuhyeon.domain.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Gallery(
    @SerialName("galleryId")
    val galleryId: Long,
    @SerialName("imageUrl")
    val imageUrl: String,
    @SerialName("title")
    val title: String
)
