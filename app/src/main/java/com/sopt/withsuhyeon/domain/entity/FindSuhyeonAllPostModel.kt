package com.sopt.withsuhyeon.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class FindSuhyeonAllPostModel(
    val region: String,
    val days: List<String>,
    val posts: List<PostItemModel>
)