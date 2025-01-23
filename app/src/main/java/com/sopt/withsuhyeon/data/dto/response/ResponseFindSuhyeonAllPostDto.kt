package com.sopt.withsuhyeon.data.dto.response

import com.sopt.withsuhyeon.domain.entity.PostItemModel
import kotlinx.serialization.Serializable

@Serializable
data class ResponseFindSuhyeonAllPostDto(
    val region: String,
    val days: List<String>,
    val posts: List<PostItemModel>
)