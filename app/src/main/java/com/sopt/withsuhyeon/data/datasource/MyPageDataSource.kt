package com.sopt.withsuhyeon.data.datasource

import com.sopt.withsuhyeon.data.dto.base.BaseResponse
import com.sopt.withsuhyeon.data.dto.response.ResponseMyPageInfoDto

interface MyPageDataSource {
    suspend fun getMyPageInfo(): BaseResponse<ResponseMyPageInfoDto>
}