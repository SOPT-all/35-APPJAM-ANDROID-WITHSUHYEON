package com.sopt.withsuhyeon.data.datasource

import com.sopt.withsuhyeon.data.dto.base.BaseResponse
import com.sopt.withsuhyeon.data.dto.response.ResponseGalleryCategoriesDto
import com.sopt.withsuhyeon.data.dto.response.ResponseGalleryPostDetailDto
import com.sopt.withsuhyeon.data.dto.response.ResponseGalleryTotalDto
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface GalleryDataSource {
    suspend fun getGalleryCategories(): BaseResponse<ResponseGalleryCategoriesDto>
    suspend fun getGalleryTotal(category: String): BaseResponse<ResponseGalleryTotalDto>
    suspend fun uploadGallery(image: MultipartBody.Part, request: RequestBody) : BaseResponse<Unit>
    suspend fun getGalleryPostDetail(galleryId: Long): BaseResponse<ResponseGalleryPostDetailDto>
    suspend fun deleteGalleryPost(galleryId: Long): BaseResponse<Unit>
}