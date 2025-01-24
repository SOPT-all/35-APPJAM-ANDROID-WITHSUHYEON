package com.sopt.withsuhyeon.feature.onboarding.state

import com.sopt.withsuhyeon.core.util.KeyStorage.EMPTY_STRING
import com.sopt.withsuhyeon.domain.entity.RegionListModel

data class SignUpState(
    val phoneNumber: String = EMPTY_STRING,
    val nickname: String = EMPTY_STRING,
    val birthYear: Int = 0,
    val gender: Boolean = true,
    val profileImage: String = EMPTY_STRING,
    val region: String = EMPTY_STRING,
    val progress: Float = 0f,
    val regionList: RegionListModel = RegionListModel(
        regions = emptyList()
    ),
    val mainLocationList: List<String> = emptyList(),
    val subLocationList: List<List<String>> = listOf(emptyList()),
    val isSuccessRequestPhoneNumber: Boolean = false,
    val isAuthNumberError: Boolean = false,
    val authNumberErrorMessage: String = EMPTY_STRING,
    val nicknameErrorState: String = EMPTY_STRING
)

