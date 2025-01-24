package com.sopt.withsuhyeon.data.dto.request

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class RequestFindSuhyeonPostUploadDto(
    val region: String,
    val gender: Boolean,
    val age: String,
    val date: LocalDateTime,
    val price: Int,
    val requests: List<String>,
    val title: String,
    val content: String
)