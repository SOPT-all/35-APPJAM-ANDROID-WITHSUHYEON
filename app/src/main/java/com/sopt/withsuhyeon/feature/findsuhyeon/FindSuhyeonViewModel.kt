package com.sopt.withsuhyeon.feature.findsuhyeon

import com.sopt.withsuhyeon.core.base.BaseViewModel
import com.sopt.withsuhyeon.feature.findsuhyeon.contract.FindSuhyeonContract.FindSuhyeonUiState
import com.sopt.withsuhyeon.feature.findsuhyeon.contract.FindSuhyeonContract.FindSuhyeonEvent
import com.sopt.withsuhyeon.feature.findsuhyeon.contract.FindSuhyeonContract.FindSuhyeonSideEffect
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FindSuhyeonViewModel @Inject constructor(
) : BaseViewModel<FindSuhyeonUiState, FindSuhyeonSideEffect, FindSuhyeonEvent>() {

    override fun createInitialState(): FindSuhyeonUiState {
        return FindSuhyeonUiState()
    }

    override suspend fun handleEvent(event: FindSuhyeonEvent) {
        when (event) {
            else -> {}
        }
    }
}