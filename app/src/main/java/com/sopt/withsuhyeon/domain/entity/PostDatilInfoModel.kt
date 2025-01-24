package com.sopt.withsuhyeon.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class PostDetailInfoModel(
    val region: String,
    val gender: Boolean,
    val age: String,
    val date: String,
    val requests: List<String>
)