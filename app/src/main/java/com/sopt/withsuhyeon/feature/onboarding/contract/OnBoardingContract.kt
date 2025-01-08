package com.sopt.withsuhyeon.feature.onboarding.contract

import com.sopt.withsuhyeon.core.base.UiEvent
import com.sopt.withsuhyeon.core.base.UiSideEffect
import com.sopt.withsuhyeon.core.base.UiState

class OnBoardingContract {
    data class OnBoardingUiState(
        val isLoading: Boolean = false,
    ) : UiState

    sealed interface OnBoardingSideEffect : UiSideEffect {
    }

    sealed interface OnBoardingEvent : UiEvent {
    }
}