package com.sopt.withsuhyeon.feature.home.state

import com.sopt.withsuhyeon.R
import com.sopt.withsuhyeon.core.util.KeyStorage.EMPTY_STRING
import com.sopt.withsuhyeon.domain.entity.HomeModel

data class HomeState(
    val homeData: HomeModel = HomeModel(
        count = 0,
        region = "",
        homePosts = emptyList(),
        nickname = EMPTY_STRING
    ),
    val count: Int = 0,
    val isRefreshing: Boolean = false,
    val lottieResId: Int = R.raw.home,
    val nickname: String = EMPTY_STRING
)