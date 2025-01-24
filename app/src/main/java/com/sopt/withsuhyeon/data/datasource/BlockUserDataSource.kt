package com.sopt.withsuhyeon.data.datasource

import com.sopt.withsuhyeon.data.dto.base.BaseResponse
import com.sopt.withsuhyeon.data.dto.response.ResponseBlockUserDto

interface BlockUserDataSource {
    suspend fun getBlockUser(): BaseResponse<ResponseBlockUserDto>
    suspend fun postBlockUser(phoneNumber: String): BaseResponse<Unit>
    suspend fun deleteBlockUser(number: String): BaseResponse<Unit>
}