package com.sopt.withsuhyeon.domain.repository

import com.sopt.withsuhyeon.domain.entity.RegionListModel


interface SignUpRepository {
    suspend fun postPhoneNumber(phoneNumber: String): Result<Unit>
    suspend fun postVerifyNumber(phoneNumber: String, verifyNumber: String): Result<Unit>
    suspend fun getRegionList() : Result<RegionListModel>
}

