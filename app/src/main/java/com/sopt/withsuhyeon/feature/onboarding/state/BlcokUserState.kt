package com.sopt.withsuhyeon.feature.onboarding.state

import com.sopt.withsuhyeon.core.util.KeyStorage.EMPTY_STRING


data class BlockUserState(
    val blockNumber: String = EMPTY_STRING
)