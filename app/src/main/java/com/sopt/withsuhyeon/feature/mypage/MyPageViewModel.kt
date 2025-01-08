package com.sopt.withsuhyeon.feature.mypage

import com.sopt.withsuhyeon.core.base.BaseViewModel
import com.sopt.withsuhyeon.feature.mypage.contract.MyPageContract.MyPageEvent
import com.sopt.withsuhyeon.feature.mypage.contract.MyPageContract.MyPageSideEffect
import com.sopt.withsuhyeon.feature.mypage.contract.MyPageContract.MyPageUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
) : BaseViewModel<MyPageUiState, MyPageSideEffect, MyPageEvent>() {

    override fun createInitialState(): MyPageUiState {
        return MyPageUiState()
    }

    override suspend fun handleEvent(event: MyPageEvent) {
        when (event) {
            else -> {}
        }
    }
}