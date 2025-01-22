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
                Log.d("88", error.message.toString())
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
                }
                .onFailure { error ->
                    _errorMessage.update { error.message }
                    _state.update { it.copy(isRefreshing = false) }
                    Log.d("88", error.message.toString())
                }
        }
    }
    fun startCountAnimation() {
        _state.value.run {
            if (count < homeData.count) {
                animationJob = viewModelScope.launch {
                    while (count < homeData.count) {
                        delay(1)
                        _state.update { state ->
                            state.copy(
                                count = minOf(count + 37, homeData.count)
                            )
                        }
                    }
                    cancelAnimation()
                }
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