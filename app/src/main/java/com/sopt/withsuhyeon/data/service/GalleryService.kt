package com.sopt.withsuhyeon.data.service

import com.sopt.withsuhyeon.data.dto.base.BaseResponse
import com.sopt.withsuhyeon.data.dto.response.ResponseGalleryCategoriesDto
import com.sopt.withsuhyeon.data.dto.response.ResponseGalleryPostDetailDto
import com.sopt.withsuhyeon.data.dto.response.ResponseGalleryTotalDto
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface GalleryService {
    @GET("/api/v1/enums/categories")
    suspend fun getGalleryCategories() : BaseResponse<ResponseGalleryCategoriesDto>

    @GET("/api/v1/galleries")
    suspend fun getGalleryTotal(
        @Query("category") category: String
    ) : BaseResponse<ResponseGalleryTotalDto>

    @Multipart
    @POST("/api/v1/galleries")
    suspend fun uploadGallery(
        @Part image: MultipartBody.Part,
        @Part("createGalleryRequest") request: RequestBody
    ) : BaseResponse<Unit>

    @GET("/api/v1/galleries/{galleryId}")
    suspend fun getGalleryPostDetail(
        @Path("galleryId") galleryId: Long
    ) : BaseResponse<ResponseGalleryPostDetailDto>

    @DELETE("/api/v1/galleries/{galleryId}")
    suspend fun deleteGalleryPost(
        @Path("galleryId") galleryId: Long
    ) : BaseResponse<Unit>
}