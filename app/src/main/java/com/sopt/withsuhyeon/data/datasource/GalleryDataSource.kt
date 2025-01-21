package com.sopt.withsuhyeon.data.datasource

import com.sopt.withsuhyeon.data.dto.base.BaseResponse
import com.sopt.withsuhyeon.data.dto.response.ResponseGalleryCategoriesDto

interface GalleryDataSource {
    suspend fun getGalleryCategories(): BaseResponse<ResponseGalleryCategoriesDto>
}