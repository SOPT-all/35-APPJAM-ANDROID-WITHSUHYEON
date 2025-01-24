package com.sopt.withsuhyeon.data.datasourceimpl

import com.sopt.withsuhyeon.data.datasource.SignUpDataSource
import com.sopt.withsuhyeon.data.dto.base.BaseResponse
import com.sopt.withsuhyeon.data.dto.request.RequestAuthNumberDto
import com.sopt.withsuhyeon.data.dto.request.RequestPhoneNumberAuthDto
import com.sopt.withsuhyeon.data.dto.request.RequestSignUpDto
import com.sopt.withsuhyeon.data.dto.response.ResponseRegionListDto
import com.sopt.withsuhyeon.data.service.SignUpService
import javax.inject.Inject

class SignUpDataSourceImpl @Inject constructor(
    private val signUpService: SignUpService
) : SignUpDataSource {
    override suspend fun postPhoneNumberAuth(phoneNumber: String): BaseResponse<Unit> =
        signUpService.postPhoneNumberAuth(
            flow = "signup",
            request = RequestPhoneNumberAuthDto(phoneNumber = phoneNumber)
        )

    override suspend fun postVerifyNumber(
        phoneNumber: String,
        verifyNumber: String
    ): BaseResponse<Unit> =
        signUpService.verifyAuthNumber(
            flow = "signup",
            request = RequestAuthNumberDto(
                phoneNumber = phoneNumber,
                verifyNumber = verifyNumber
            )
        )

    override suspend fun getRegionList(): BaseResponse<ResponseRegionListDto> =
        signUpService.getRegionList()

    override suspend fun postSignUp(
        phoneNumber: String,
        nickname: String,
        birthYear: Int,
        gender: Boolean,
        profileImage: String,
        region: String
    ): BaseResponse<Unit> =
        signUpService.postSignUp(
            requestSignUpDto = RequestSignUpDto(
                phoneNumber = phoneNumber,
                nickname = nickname,
                birthYear = birthYear,
                gender = gender,
                profileImage = profileImage,
                region = region
            )
        )
}
