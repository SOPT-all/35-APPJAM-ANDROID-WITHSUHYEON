package com.sopt.withsuhyeon.domain.repository

import com.sopt.withsuhyeon.domain.entity.MyPageInfo

interface MyPageRepository {
    suspend fun getMyPageInfo() : Result<MyPageInfo>
}