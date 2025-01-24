package com.sopt.withsuhyeon.data.mapper

import com.sopt.withsuhyeon.data.dto.response.ResponseHomeDto
import com.sopt.withsuhyeon.domain.entity.HomeModel

fun ResponseHomeDto.toHomeModel(): HomeModel {
    return HomeModel(
        count = this.count,
        region = this.region,
        homePosts = this.homePosts,
    )
}