package com.sopt.withsuhyeon.data.dto.response

import com.sopt.withsuhyeon.domain.entity.Category
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseGalleryCategoriesDto(
    @SerialName("categories")
    val categories: List<Category>
)