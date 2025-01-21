package com.sopt.withsuhyeon.domain.entity

import com.sopt.withsuhyeon.core.type.MediumChipType

data class PostDetailInfoModel(
    val region: String,
    val gender: Boolean,
    val age: String,
    val date: String,
    val price: Int,
    val requests: List<MediumChipType>
)