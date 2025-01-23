package com.sopt.withsuhyeon.data.datasourceimpl

import com.sopt.withsuhyeon.data.datasource.FindSuhyeonDataSource
import com.sopt.withsuhyeon.data.dto.base.BaseResponse
import com.sopt.withsuhyeon.data.dto.request.RequestFindSuhyeonPostUploadDto
import com.sopt.withsuhyeon.data.dto.response.ResponseFindSuhyeonAllPostDto
import com.sopt.withsuhyeon.data.dto.response.ResponseRegionListDto
import com.sopt.withsuhyeon.data.service.FindSuhyeonService
import javax.inject.Inject

class FindSuhyeonDataSourceImpl @Inject constructor(
    private val findSuhyeonService: FindSuhyeonService
) : FindSuhyeonDataSource {

    override suspend fun getFindSuhyeonAllPost(regionType: String, date: String): BaseResponse<ResponseFindSuhyeonAllPostDto> =
        findSuhyeonService.getFindSuhyeonAllPost(regionType, date)

    override suspend fun getRegionList(): BaseResponse<ResponseRegionListDto> =
        findSuhyeonService.getRegionList()

    override suspend fun postFindSuhyeonUpload(request: RequestFindSuhyeonPostUploadDto): BaseResponse<Unit> =
        findSuhyeonService.postFindSuhyeonUpload(
            request = request
        )

}