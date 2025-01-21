package com.sopt.withsuhyeon.data.datasource

import com.sopt.withsuhyeon.data.dto.base.BaseResponse
import com.sopt.withsuhyeon.data.dto.response.ResponseGalleryCategoriesDto
import com.sopt.withsuhyeon.data.dto.response.ResponseGalleryTotalDto

interface GalleryDataSource {
    suspend fun getGalleryCategories(): BaseResponse<ResponseGalleryCategoriesDto>
    suspend fun getGalleryTotal(category: String): BaseResponse<ResponseGalleryTotalDto>
}