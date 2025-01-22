package com.sopt.withsuhyeon.data.datasource

import com.sopt.withsuhyeon.data.dto.base.BaseResponse
import com.sopt.withsuhyeon.data.dto.response.ResponseHomeDto

interface HomeDataSource {
    suspend fun getHomeData(): BaseResponse<ResponseHomeDto>
}