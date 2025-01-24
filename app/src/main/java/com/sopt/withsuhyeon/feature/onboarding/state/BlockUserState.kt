package com.sopt.withsuhyeon.feature.onboarding.state

import com.sopt.withsuhyeon.core.util.KeyStorage.EMPTY_STRING


data class BlockUserState(
    val blockNumber: String = EMPTY_STRING,
    val blockNumbers: List<String> = emptyList(),
    val nickname: String = EMPTY_STRING,
    val errorMessage: String = EMPTY_STRING
)