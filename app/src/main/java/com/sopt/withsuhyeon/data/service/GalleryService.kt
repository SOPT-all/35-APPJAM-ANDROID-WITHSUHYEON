package com.sopt.withsuhyeon.data.service

import com.sopt.withsuhyeon.data.dto.base.BaseResponse
import com.sopt.withsuhyeon.data.dto.response.ResponseGalleryCategoriesDto
import retrofit2.http.GET

interface GalleryService {
    @GET("/api/v1/enums/categories")
    suspend fun getGalleryCategories() : BaseResponse<ResponseGalleryCategoriesDto>
}