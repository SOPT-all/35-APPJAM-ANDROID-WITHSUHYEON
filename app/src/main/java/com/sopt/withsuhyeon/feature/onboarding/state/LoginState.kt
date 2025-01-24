package com.sopt.withsuhyeon.feature.onboarding.state

import com.sopt.withsuhyeon.core.util.KeyStorage.EMPTY_STRING

data class LoginState(
    val phoneNumber: String = EMPTY_STRING,
    val authNumber: String = EMPTY_STRING,
    val accessToken: String = EMPTY_STRING,
    val refreshToken: String = EMPTY_STRING,
    val isSuccessRequestPhoneNumber: Boolean = false,
    val isAuthNumberError: Boolean = false,
    val authNumberErrorMessage: String = EMPTY_STRING,
)