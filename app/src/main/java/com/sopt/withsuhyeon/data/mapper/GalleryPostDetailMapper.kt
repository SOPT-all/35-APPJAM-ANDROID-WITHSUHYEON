package com.sopt.withsuhyeon.data.mapper

import com.sopt.withsuhyeon.data.dto.response.ResponseGalleryPostDetailDto
import com.sopt.withsuhyeon.domain.entity.GalleryPostDetailModel

fun ResponseGalleryPostDetailDto.toGalleryPostDetailModel(): GalleryPostDetailModel {
    return GalleryPostDetailModel(
        imageUrl = this.imageUrl,
        category = this.category,
        title = this.title,
        profileImage = this.profileImage,
        nickname = this.nickname,
        createdAt = this.createdAt,
        content = this.content
    )
}