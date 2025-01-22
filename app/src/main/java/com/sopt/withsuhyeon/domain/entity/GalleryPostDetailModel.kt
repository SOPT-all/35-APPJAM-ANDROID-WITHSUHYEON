package com.sopt.withsuhyeon.domain.entity

data class GalleryPostDetailModel(
    val imageUrl: String,
    val category: String,
    val title: String,
    val profileImage: String,
    val nickname: String,
    val createdAt: String,
    val content: String
)