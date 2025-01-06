package com.sopt.withsuhyeon.data.mapper

import com.sopt.withsuhyeon.data.dto.response.ResponseDummyDto
import com.sopt.withsuhyeon.data.dto.response.ReviewItem
import com.sopt.withsuhyeon.data.dto.response.UserDto
import com.sopt.withsuhyeon.domain.entity.DummyModel
import com.sopt.withsuhyeon.domain.entity.UserModel

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

fun UserDto.toUserModel() = UserModel(
    id = this.id,
    email = this.email,
    firstName = this.firstName,
    lastName = this.lastName,
    avatar = this.avatar
)