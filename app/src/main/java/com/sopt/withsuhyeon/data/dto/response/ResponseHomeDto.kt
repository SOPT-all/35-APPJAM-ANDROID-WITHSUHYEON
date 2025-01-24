package com.sopt.withsuhyeon.data.dto.response

import com.sopt.withsuhyeon.domain.entity.PostItemModel
import kotlinx.serialization.Serializable

@Serializable
data class ResponseHomeDto(
    val count: Int,
    val region: String,
    val homePosts: List<PostItemModel>
)