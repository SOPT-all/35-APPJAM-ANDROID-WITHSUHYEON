package com.sopt.withsuhyeon.domain.entity

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class FindSuhyeonPostUploadModel(
    val region: String,
    val gender: Boolean,
    val age: String,
    val date: LocalDateTime,
    val price: Int,
    val requests: List<String>,
    val title: String,
    val content: String
)