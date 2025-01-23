package com.sopt.withsuhyeon.domain.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BlockUserModel(
    @SerialName("nickname")
    val nickname: String,
    @SerialName("phoneNumbers")
    val phoneNumbers: List<String>
)