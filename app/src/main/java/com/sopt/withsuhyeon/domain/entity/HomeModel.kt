package com.sopt.withsuhyeon.domain.entity

data class HomeModel(
    val count: Int,
    val region: String,
    val homePosts: List<HomePostItemModel>
)