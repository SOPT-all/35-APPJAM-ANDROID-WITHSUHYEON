package com.sopt.withsuhyeon.data.datasourceimpl

import com.sopt.withsuhyeon.data.datasource.MyPageDataSource
import com.sopt.withsuhyeon.data.dto.base.BaseResponse
import com.sopt.withsuhyeon.data.dto.response.ResponseMyPageInfoDto
import com.sopt.withsuhyeon.data.service.MyPageService
import javax.inject.Inject

class MyPageDataSourceImpl @Inject constructor(
    private val myPageService: MyPageService
) : MyPageDataSource {
    override suspend fun getMyPageInfo(): BaseResponse<ResponseMyPageInfoDto> =
        myPageService.getMyPageInfo()
}