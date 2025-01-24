package com.sopt.withsuhyeon.feature.onboarding

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.withsuhyeon.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _userId = MutableStateFlow<Int?>(null)
    val userId: StateFlow<Int?> = _userId.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    init {
        getUserId()
    }

    fun getUserId() {
        viewModelScope.launch {
            userRepository.connectWithUserId()
                .onSuccess {
                    Log.d("userId", "success")
                }
                .onFailure { error ->
                    _errorMessage.update { error.message }
                }
        }
    }
}