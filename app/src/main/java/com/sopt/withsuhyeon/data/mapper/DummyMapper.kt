package com.sopt.withsuhyeon.data.mapper

import com.sopt.withsuhyeon.data.dto.response.ResponseDummyDto
import com.sopt.withsuhyeon.data.dto.response.ReviewItem
import com.sopt.withsuhyeon.domain.entity.DummyModel

fun ResponseDummyDto.toDummyListModel(): List<DummyModel> {
    return reviews.map { reviewItem ->
        reviewItem.toDummyModel()
    }
}

fun ReviewItem.toDummyModel() = DummyModel(
    userName = this.userName,
    content = this.content,
    score = this.score,
    isAdd = this.isAdd,
    image1 = this.image1,
    image2 = this.image2,
    image3 = this.image3,
    createdAt = this.createdAt
)