package com.sopt.withsuhyeon.data.dto.response

import com.sopt.withsuhyeon.domain.entity.Gallery
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseGalleryTotalDto(
    @SerialName("galleries")
    val galleries: List<Gallery>
)
