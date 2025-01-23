package com.sopt.withsuhyeon.data.service

import com.sopt.withsuhyeon.data.dto.base.BaseResponse
import com.sopt.withsuhyeon.data.dto.request.RequestFindSuhyeonPostUploadDto
import com.sopt.withsuhyeon.data.dto.response.ResponseFindSuhyeonAllPostDto
import com.sopt.withsuhyeon.data.dto.response.ResponseRegionListDto
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface FindSuhyeonService {
    @GET("/api/v1/posts")
    suspend fun getFindSuhyeonAllPost(
        @Query("region") regionType: String,
        @Query("date") date: String,
    ) : BaseResponse<ResponseFindSuhyeonAllPostDto>

    @GET("/api/v1/enums/regions")
    suspend fun getRegionList(
    ) : BaseResponse<ResponseRegionListDto>

    @POST("/api/v1/posts")
    suspend fun postFindSuhyeonUpload(
        @Body request: RequestFindSuhyeonPostUploadDto
    ) : BaseResponse<Unit>

}