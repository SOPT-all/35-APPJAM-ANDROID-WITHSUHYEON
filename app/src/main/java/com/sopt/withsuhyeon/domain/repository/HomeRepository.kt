package com.sopt.withsuhyeon.domain.repository

import com.sopt.withsuhyeon.domain.entity.HomeModel

interface HomeRepository {
    suspend fun getHomeData() : Result<HomeModel>
}