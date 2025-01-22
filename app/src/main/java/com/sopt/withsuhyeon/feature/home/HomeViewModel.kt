package com.sopt.withsuhyeon.feature.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.withsuhyeon.domain.repository.HomeRepository
import com.sopt.withsuhyeon.feature.home.state.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository
) : ViewModel() {
    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    private var animationJob: Job? = null

    suspend fun getHomeData() {
        homeRepository.getHomeData()
            .onSuccess { homeData ->
                _state.update {
                    it.copy(
                        homeData = homeData
                    )
                }
            }
            .onFailure { error ->
                _errorMessage.update { error.message }
            }
    }
    fun refreshHomeData() {
        viewModelScope.launch {
            _state.update { it.copy(isRefreshing = true) }
            homeRepository.getHomeData()
                .onSuccess { homeData ->
                    _state.update {
                        it.copy(
                            homeData = homeData
                        )
                    }
                    _state.update { it.copy(isRefreshing = false) }
                    startCountAnimation()
                }
                .onFailure { error ->
                    _errorMessage.update { error.message }
                    _state.update { it.copy(isRefreshing = false) }
                }
        }
    }
    fun startCountAnimation() {
        val target = _state.value.homeData.count

        animationJob = viewModelScope.launch {
            while (_state.value.count < target) {
                val current = _state.value.count
                val difference = target - current

                val step = (difference * 0.1).coerceAtLeast(1.0).toInt()
                _state.update { state ->
                    state.copy(count = minOf(current + step, target))
                }
                delay(30)
            }
            cancelAnimation()
        }
    }

    private fun cancelAnimation() {
        animationJob?.cancel()
    }

}