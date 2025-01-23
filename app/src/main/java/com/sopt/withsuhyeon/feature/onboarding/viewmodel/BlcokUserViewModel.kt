package com.sopt.withsuhyeon.feature.onboarding.viewmodel

import androidx.lifecycle.ViewModel
import com.sopt.withsuhyeon.feature.onboarding.state.BlockUserState
import com.sopt.withsuhyeon.feature.onboarding.state.SignUpState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class BlcokUserViewModel @Inject constructor(

) :ViewModel() {
    private val _blockUserState = MutableStateFlow(BlockUserState())
    val signUpState: StateFlow<BlockUserState> = _blockUserState

}