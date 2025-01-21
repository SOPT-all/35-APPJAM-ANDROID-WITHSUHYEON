package com.sopt.withsuhyeon.data.service

import com.sopt.withsuhyeon.data.dto.base.BaseResponse
import com.sopt.withsuhyeon.data.dto.response.ResponseGalleryCategoriesDto
import com.sopt.withsuhyeon.data.dto.response.ResponseGalleryTotalDto
import retrofit2.http.GET
import retrofit2.http.Query

interface GalleryService {
    @GET("/api/v1/enums/categories")
    suspend fun getGalleryCategories() : BaseResponse<ResponseGalleryCategoriesDto>

    @GET("/api/v1/galleries")
    suspend fun getGalleryTotal(
        @Query("category") category: String
    ) : BaseResponse<ResponseGalleryTotalDto>

//    @Multipart
//    @POST("/api/v1/galleries")
//    suspend fun uploadGallery(
//        @Header("Authorization") header: String,
//        @Part image: MultipartBody.Part,
//        @Body request: RequestUploadGalleryDto
//    ) : BaseResponse<Unit>
}