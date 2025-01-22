package com.sopt.withsuhyeon.data.datasourceimpl

import com.sopt.withsuhyeon.data.datasource.GalleryDataSource
import com.sopt.withsuhyeon.data.dto.base.BaseResponse
import com.sopt.withsuhyeon.data.dto.response.ResponseGalleryCategoriesDto
import com.sopt.withsuhyeon.data.dto.response.ResponseGalleryPostDetailDto
import com.sopt.withsuhyeon.data.dto.response.ResponseGalleryTotalDto
import com.sopt.withsuhyeon.data.service.GalleryService
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

class GalleryDataSourceImpl @Inject constructor(
    private val galleryService: GalleryService
) : GalleryDataSource {
    override suspend fun getGalleryCategories(): BaseResponse<ResponseGalleryCategoriesDto> =
        galleryService.getGalleryCategories()

    override suspend fun getGalleryTotal(category: String): BaseResponse<ResponseGalleryTotalDto> =
        galleryService.getGalleryTotal(category)

    override suspend fun uploadGallery(image: MultipartBody.Part, request: RequestBody): BaseResponse<Unit> =
        galleryService.uploadGallery(image, request)

    override suspend fun getGalleryPostDetail(galleryId: Long): BaseResponse<ResponseGalleryPostDetailDto> =
       galleryService.getGalleryPostDetail(galleryId)
}