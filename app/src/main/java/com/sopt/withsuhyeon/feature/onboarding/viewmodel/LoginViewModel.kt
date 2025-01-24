package com.sopt.withsuhyeon.feature.onboarding.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.withsuhyeon.core.network.TokenManager
import com.sopt.withsuhyeon.data.dto.base.BaseResponse
import com.sopt.withsuhyeon.domain.repository.LoginRepository
import com.sopt.withsuhyeon.feature.onboarding.state.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json.Default.decodeFromString
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val tokenManager: TokenManager,
    private val loginRepository: LoginRepository
) : ViewModel() {
    private val _loginState = MutableStateFlow(LoginState())
    val loginState: StateFlow<LoginState> = _loginState

    fun updatePhoneNumber(phoneNumber: String) {
        _loginState.update {
            it.copy(
                phoneNumber = phoneNumber,
            )
        }
    }

    fun updateAuthNumber(authNumber: String) {
        _loginState.update {
            it.copy(
                authNumber = authNumber,
            )
        }
    }

    fun updateToken(accessToken: String, refreshToken: String) {
        _loginState.update {
            it.copy(
                accessToken = accessToken,
                refreshToken = refreshToken
            )
        }
    }

    fun postPhoneNumberAuth(phoneNumber: String) {
        viewModelScope.launch {
            loginRepository.postPhoneNumber(phoneNumber).onSuccess {
                Log.d("log", "성공")
            }.onFailure { error ->
                Log.d("log", error.message.toString())
            }
        }
    }

    fun postVerifyNumberAuth(
        phoneNumber: String,
        verifyNumber: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        viewModelScope.launch {
            loginRepository.postVerifyNumber(
                phoneNumber,
                verifyNumber
            ).onSuccess {
                onSuccess()
            }.onFailure { error ->
                val errorMessage = when (error) {
                    is retrofit2.HttpException -> {
                        val errorBody = error.response()?.errorBody()?.string()
                        try {
                            val baseResponse =
                                decodeFromString<BaseResponse<Unit>>(errorBody ?: "")
                            baseResponse.message ?: "알 수 없는 에러가 발생했습니다."
                        } catch (e: Exception) {
                            "에러 응답 파싱 실패: ${e.message}"
                        }
                    }

                    else -> {
                        "알 수 없는 에러가 발생했습니다: ${error.message}"
                    }
                }
                onError(errorMessage)
                _loginState.update {
                    it.copy(

                    )
                }
            }
        }
    }

    fun postLogin() {
        viewModelScope.launch {
            loginRepository.postLogin(
                _loginState.value.phoneNumber
            ).onSuccess { loginToken ->
                _loginState.update { current ->
                    current.copy(
                        accessToken = loginToken.accessToken,
                        refreshToken = loginToken.refreshToken
                    )
                }
                tokenManager.saveToken(loginToken.accessToken)
                Log.d("log", "${_loginState.value.accessToken} ${_loginState.value.refreshToken}")
            }.onFailure { error ->
                Log.d("log", error.message.toString())
            }
        }
    }
}