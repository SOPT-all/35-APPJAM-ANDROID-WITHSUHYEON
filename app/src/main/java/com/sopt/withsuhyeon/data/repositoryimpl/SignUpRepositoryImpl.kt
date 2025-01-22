package com.sopt.withsuhyeon.data.repositoryimpl

import com.sopt.withsuhyeon.data.dto.request.RequestPhoneNumberAuthDto
import com.sopt.withsuhyeon.data.service.SignUpService
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
}
