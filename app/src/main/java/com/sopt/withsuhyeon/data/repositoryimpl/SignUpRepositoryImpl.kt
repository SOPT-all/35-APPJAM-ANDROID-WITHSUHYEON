package com.sopt.withsuhyeon.data.repositoryimpl

import com.sopt.withsuhyeon.data.dto.request.RequestAuthNumberDto
import com.sopt.withsuhyeon.data.dto.request.RequestPhoneNumberAuthDto
import com.sopt.withsuhyeon.data.dto.request.RequestSignUpDto
import com.sopt.withsuhyeon.data.mapper.toRegionListModel
import com.sopt.withsuhyeon.data.service.SignUpService
import com.sopt.withsuhyeon.domain.entity.RegionListModel
import com.sopt.withsuhyeon.domain.repository.SignUpRepository
import javax.inject.Inject

class SignUpRepositoryImpl @Inject constructor(
    private val signUpService: SignUpService
) : SignUpRepository {
    override suspend fun postPhoneNumber(phoneNumber: String): Result<Unit> =
        runCatching {
            val response = signUpService.postPhoneNumberAuth(
                flow = "signup",
                request = RequestPhoneNumberAuthDto(phoneNumber = phoneNumber)
            )
            response.message
        }

    override suspend fun postVerifyNumber(phoneNumber: String, verifyNumber: String): Result<Unit> =
        runCatching {
            val response = signUpService.verifyAuthNumber(
                flow = "signup",
                request = RequestAuthNumberDto(
                    phoneNumber = phoneNumber,
                    verifyNumber = verifyNumber
                )
            )
            response.message
        }

    override suspend fun getRegionList(): Result<RegionListModel> =
        runCatching {
            val response = signUpService.getRegionList()
            response.result?.toRegionListModel() ?: throw Exception("Response data is null")
        }

    override suspend fun postSignUp(
        phoneNumber: String,
        nickname: String,
        birthYear: Int,
        gender: Boolean,
        profileImage: String,
        region: String
    ): Result<Unit> = runCatching {
        val response = signUpService.postSignUp(
            requestSignUpDto = RequestSignUpDto(
                phoneNumber = phoneNumber,
                nickname = nickname,
                birthYear = birthYear,
                gender = gender,
                profileImage = profileImage,
                region = region
            )
        )
        response.message
    }
}
