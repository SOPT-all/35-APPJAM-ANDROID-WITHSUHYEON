package com.sopt.withsuhyeon.domain.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Category(
    @SerialName("imageUrl")
    val imageUrl: String,
    @SerialName("category")
    val category: String
)
