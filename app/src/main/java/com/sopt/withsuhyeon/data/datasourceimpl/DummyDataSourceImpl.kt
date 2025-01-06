package com.sopt.withsuhyeon.data.datasourceimpl

import com.sopt.withsuhyeon.data.datasource.DummyDataSource
import com.sopt.withsuhyeon.data.dto.base.BaseResponse
import com.sopt.withsuhyeon.data.dto.response.ResponseDummyDto
import com.sopt.withsuhyeon.data.dto.response.ResponseUserDto
import com.sopt.withsuhyeon.data.service.DummyService
import javax.inject.Inject

class DummyDataSourceImpl @Inject constructor(
    private val dummyService: DummyService
) : DummyDataSource {
    override suspend fun getProductReviews(request: Number): BaseResponse<ResponseDummyDto> =
        dummyService.getProductReviews(request)

    override suspend fun postProductsLike(productId: Int, memberId: Int): BaseResponse<String?> =
        dummyService.postProductLike(productId, memberId)

    override suspend fun getUserDetails(userId: Int): ResponseUserDto =
        dummyService.getUserDetails(userId)
}