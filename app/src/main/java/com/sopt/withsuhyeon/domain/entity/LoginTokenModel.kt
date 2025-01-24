package com.sopt.withsuhyeon.domain.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginTokenModel (
    @SerialName("accessToken")
    val accessToken: String,
    @SerialName("refreshToken")
    val refreshToken: String
)