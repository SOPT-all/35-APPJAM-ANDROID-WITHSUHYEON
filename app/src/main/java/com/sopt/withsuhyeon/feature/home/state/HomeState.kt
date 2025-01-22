package com.sopt.withsuhyeon.feature.home.state

import com.sopt.withsuhyeon.R
import com.sopt.withsuhyeon.domain.entity.PostItemModel

data class HomeState(
    val count: Int = 0,
    val countTarget: Int= 4357,
    val isRefreshing: Boolean = false,
    val postList: List<PostItemModel> = emptyList(),
    val lottieResId: Int = R.raw.home,
)