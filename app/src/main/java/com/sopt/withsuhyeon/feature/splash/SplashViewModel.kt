package com.sopt.withsuhyeon.feature.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.withsuhyeon.core.network.TokenManager
import com.sopt.withsuhyeon.feature.splash.state.SplashState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel@Inject constructor(
    private val tokenManager: TokenManager,
) : ViewModel()  {
    private val _state = MutableStateFlow(SplashState())
    val state: StateFlow<SplashState> = _state
    fun getToken() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    token = tokenManager.getToken()
                )
            }

        }
    }
}