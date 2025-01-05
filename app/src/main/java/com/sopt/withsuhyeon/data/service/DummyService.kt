package com.sopt.withsuhyeon.data.service

import com.sopt.withsuhyeon.data.dto.base.BaseResponse
import com.sopt.withsuhyeon.data.dto.response.ResponseDummyDto
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface DummyService {
    companion object {
        const val API = "api"
        const val V1 = "v1"
        const val PRODUCTS = "products"
        const val LIKES = "likes"
        const val REVIEWS = "reviews"
    }

    @GET("/$API/$V1/$PRODUCTS/{productId}/$REVIEWS")
    suspend fun getProductReviews(
        @Path("productId") productId : Number
    ) : BaseResponse<ResponseDummyDto>

    @POST("$API/$V1/$LIKES/$PRODUCTS/{productId}")
    suspend fun postProductLike(
        @Path("productId") productId : Int,
        @Header("memberId") memberId : Int,
    ): BaseResponse<String?>
}