package com.sopt.withsuhyeon.domain.entity

data class PostItemModel(
    val postId: Long,
    val title: String,
    val price: Int,
    val gender: Boolean,
    val age: String,
    val date: String,
    val matching: Boolean
)