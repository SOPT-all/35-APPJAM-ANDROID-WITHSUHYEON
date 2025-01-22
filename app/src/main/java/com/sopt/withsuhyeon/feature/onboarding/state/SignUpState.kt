package com.sopt.withsuhyeon.feature.onboarding.state

import com.sopt.withsuhyeon.core.util.KeyStorage.EMPTY_STRING

data class SignUpState(
    val phoneNumber: String = EMPTY_STRING,
    val nickname: String = EMPTY_STRING,
    val birthYear: Int? = null,
    val gender: Boolean? = null,
    val profileImage: String = EMPTY_STRING,
    val region: String = EMPTY_STRING,
    val progress: Float = 0f,

//    완료 제약조건
//    phoneNumber.length == 11,
//    nickname.length in 2..12,
//    birthYear != null,
//    gender != null,
//    profileImage.length == 5,
//    region.isNotEmpty()
)
