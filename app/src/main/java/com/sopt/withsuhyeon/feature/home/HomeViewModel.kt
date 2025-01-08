package com.sopt.withsuhyeon.feature.home

import com.sopt.withsuhyeon.core.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.sopt.withsuhyeon.feature.home.contract.HomeContract.HomeUiState
import com.sopt.withsuhyeon.feature.home.contract.HomeContract.HomeSideEffect
import com.sopt.withsuhyeon.feature.home.contract.HomeContract.HomeEvent

@HiltViewModel
class HomeViewModel @Inject constructor(
) : BaseViewModel<HomeUiState, HomeSideEffect, HomeEvent>() {

    override fun createInitialState(): HomeUiState {
        return HomeUiState()
    }

    override suspend fun handleEvent(event: HomeEvent) {
        when (event) {
            else -> {}
        }
    }
}