package com.sopt.withsuhyeon.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.withsuhyeon.R
import com.sopt.withsuhyeon.core.util.KeyStorage.AGE_20_TO_24
import com.sopt.withsuhyeon.domain.entity.PostItemModel
import com.sopt.withsuhyeon.feature.home.state.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
) : ViewModel() {
    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state

    private var animationJob: Job? = null

    init {
        _state.update { state ->
            state.copy(
                lottieResId = R.raw.home,
                postList = List(3) {
                    PostItemModel(
                        postId = it.toLong(),
                        title = "서울역 수현이 구해요ㅠㅠ",
                        price = 5000,
                        gender = true,
                        age = AGE_20_TO_24,
                        date = "1월 25일 (토) 오후 2:30",
                        matching = true
                    )
                }
            )
        }
    }

    fun startCountAnimation() {
        if (_state.value.count < _state.value.countTarget) {
            animationJob = viewModelScope.launch {
                while (_state.value.count < _state.value.countTarget) {
                    delay(1)
                    _state.update { state ->
                        state.copy(
                            count = minOf(_state.value.count + 37, _state.value.countTarget)
                        )
                    }
                }
                cancelAnimation()
            }
        }
    }

    private fun cancelAnimation() {
        animationJob?.cancel()
    }

    fun onRefresh() {
        _state.update { it.copy(isRefreshing = true) }
        viewModelScope.launch {
            delay(500)
            _state.update { it.copy(isRefreshing = false) }
        }
    }
}