package com.sopt.withsuhyeon.data.datasource

import com.sopt.withsuhyeon.data.dto.base.BaseResponse
import com.sopt.withsuhyeon.data.dto.response.ResponseDummyDto
import com.sopt.withsuhyeon.data.dto.response.ResponseUserDto

interface DummyDataSource {
    suspend fun getProductReviews(request: Number): BaseResponse<ResponseDummyDto>
    suspend fun postProductsLike(productId : Int, memberId : Int): BaseResponse<String?>
    suspend fun getUserDetails(userId: Int): ResponseUserDto
}