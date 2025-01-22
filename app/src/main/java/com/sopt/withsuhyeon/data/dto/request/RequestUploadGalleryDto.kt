package com.sopt.withsuhyeon.data.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestUploadGalleryDto(
    @SerialName("category")
    val category: String,
    @SerialName("title")
    val title: String,
    @SerialName("content")
    val content: String
)
