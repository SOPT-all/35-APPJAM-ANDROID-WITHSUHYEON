package com.sopt.withsuhyeon.domain.repository

import com.sopt.withsuhyeon.data.dto.base.BaseResponse
import com.sopt.withsuhyeon.domain.entity.RegionListModel


interface SignUpRepository {
    suspend fun postPhoneNumber(phoneNumber: String): Result<Unit>
    suspend fun postVerifyNumber(phoneNumber: String, verifyNumber: String): Result<BaseResponse<Unit>>
    suspend fun getRegionList(): Result<RegionListModel>
    suspend fun postSignUp(
        phoneNumber: String,
        nickname: String,
        birthYear: Int,
        gender: Boolean,
        profileImage: String,
        region: String
    ): Result<Unit>
}

