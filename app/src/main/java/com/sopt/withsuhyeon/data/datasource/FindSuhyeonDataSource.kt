package com.sopt.withsuhyeon.data.datasource

import com.sopt.withsuhyeon.data.dto.base.BaseResponse
import com.sopt.withsuhyeon.data.dto.response.ResponseFindSuhyeonAllPostDto
import com.sopt.withsuhyeon.data.dto.response.ResponseRegionListDto

interface FindSuhyeonDataSource {
    suspend fun getFindSuhyeonAllPost(regionType: String, date: String): BaseResponse<ResponseFindSuhyeonAllPostDto>
    suspend fun getRegionList(): BaseResponse<ResponseRegionListDto>
}