package com.sopt.withsuhyeon.data.mapper

import com.sopt.withsuhyeon.data.dto.request.RequestFindSuhyeonPostUploadDto
import com.sopt.withsuhyeon.domain.entity.FindSuhyeonPostUploadModel

fun FindSuhyeonPostUploadModel.toRequestFindSuhyeonPostUploadDto(): RequestFindSuhyeonPostUploadDto {
    return RequestFindSuhyeonPostUploadDto(
        region = this.region,
        gender = this.gender,
        age = this.age,
        date = this.date,
        price = this.price,
        requests = this.requests,
        title = this.title,
        content = this.content
    )
}