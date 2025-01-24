package com.sopt.withsuhyeon.data.mapper

import com.sopt.withsuhyeon.data.dto.response.ResponseFindSuhyeonAllPostDto
import com.sopt.withsuhyeon.domain.entity.FindSuhyeonAllPostModel

fun ResponseFindSuhyeonAllPostDto.toFindSuhyeonAllPostModel(): FindSuhyeonAllPostModel {
    return FindSuhyeonAllPostModel(
        region = this.region,
        days = this.days,
        posts = this.posts
    )
}