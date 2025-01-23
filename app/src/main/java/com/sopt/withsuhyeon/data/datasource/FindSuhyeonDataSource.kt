package com.sopt.withsuhyeon.data.datasource

import com.sopt.withsuhyeon.data.dto.base.BaseResponse
import com.sopt.withsuhyeon.data.dto.request.RequestFindSuhyeonPostUploadDto
import com.sopt.withsuhyeon.data.dto.response.ResponseFindSuhyeonAllPostDto
import com.sopt.withsuhyeon.data.dto.response.ResponseRegionListDto

interface FindSuhyeonDataSource {
    suspend fun getFindSuhyeonAllPost(regionType: String, date: String): BaseResponse<ResponseFindSuhyeonAllPostDto>
    suspend fun getRegionList(): BaseResponse<ResponseRegionListDto>
    suspend fun postFindSuhyeonUpload(request: RequestFindSuhyeonPostUploadDto): BaseResponse<Unit>
    suspend fun getFindSuhyeonPostDetail(postId: Long): BaseResponse<ResponseFindSuhyeonPostDetailDto>
    suspend fun deleteFindSuhyeonPost(postId: Long): BaseResponse<Unit>
}