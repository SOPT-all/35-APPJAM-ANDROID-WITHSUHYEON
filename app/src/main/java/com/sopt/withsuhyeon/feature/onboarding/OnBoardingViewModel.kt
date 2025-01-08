package com.sopt.withsuhyeon.feature.onboarding

import com.sopt.withsuhyeon.core.base.BaseViewModel
import com.sopt.withsuhyeon.feature.onboarding.contract.OnBoardingContract.OnBoardingEvent
import com.sopt.withsuhyeon.feature.onboarding.contract.OnBoardingContract.OnBoardingSideEffect
import com.sopt.withsuhyeon.feature.onboarding.contract.OnBoardingContract.OnBoardingUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
) : BaseViewModel<OnBoardingUiState, OnBoardingSideEffect, OnBoardingEvent>() {

    override fun createInitialState(): OnBoardingUiState {
        return OnBoardingUiState()
    }

    override suspend fun handleEvent(event: OnBoardingEvent) {
        when (event) {
            else -> {}
        }
    }
}