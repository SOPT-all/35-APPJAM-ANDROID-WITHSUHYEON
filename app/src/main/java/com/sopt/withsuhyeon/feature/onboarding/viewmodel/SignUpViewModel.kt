package com.sopt.withsuhyeon.feature.onboarding.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.withsuhyeon.core.util.KeyStorage.DEFAULT
import com.sopt.withsuhyeon.core.util.KeyStorage.EMPTY_STRING
import com.sopt.withsuhyeon.core.util.KeyStorage.LENGTH_ERROR
import com.sopt.withsuhyeon.core.util.KeyStorage.LENGTH_ERROR_MESSAGE
import com.sopt.withsuhyeon.core.util.KeyStorage.SPECIAL_CHARACTER_ERROR
import com.sopt.withsuhyeon.core.util.KeyStorage.SPECIAL_CHARACTER_ERROR_MESSAGE
import com.sopt.withsuhyeon.core.util.regex.containsSpecialCharacters
import com.sopt.withsuhyeon.data.dto.base.BaseResponse
import com.sopt.withsuhyeon.domain.repository.SignUpRepository
import com.sopt.withsuhyeon.feature.onboarding.state.SignUpState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json.Default.decodeFromString
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpRepository: SignUpRepository
) : ViewModel() {
    private val _signUpState = MutableStateFlow(SignUpState())
    val signUpState: StateFlow<SignUpState> = _signUpState

    fun updatePhoneNumber(phoneNumber: String) {
        _signUpState.update {
            it.copy(
                phoneNumber = phoneNumber,
            )
        }
    }

    fun updateRequestPhoneNumberState(state: Boolean) {
        _signUpState.update {
            it.copy(
                isSuccessRequestPhoneNumber = state
            )
        }
    }

    fun updateNickname(nickname: String) {
        val errorState = when {
            nickname.containsSpecialCharacters() -> SPECIAL_CHARACTER_ERROR_MESSAGE
            nickname.length !in 2..12 -> LENGTH_ERROR_MESSAGE
            else -> EMPTY_STRING
        }

        _signUpState.update {
            it.copy(
                nickname = nickname,
                nicknameErrorState = errorState
            )
        }
    }

    fun updateBirthYear(birthYear: Int) {
        _signUpState.update {
            it.copy(
                birthYear = birthYear,
            )
        }
    }

    fun updateGender(gender: Boolean) {
        _signUpState.update {
            it.copy(
                gender = gender,
            )
        }
    }

    fun updateProfileImage(profileImage: String) {
        _signUpState.update {
            it.copy(
                profileImage = profileImage,
            )
        }
    }

    fun updateRegion(region: String) {
        _signUpState.update {
            it.copy(
                region = region,
            )
        }
    }

    fun updateProgress(currentProgress: Float) {
        _signUpState.update {
            it.copy(
                progress = currentProgress
            )
        }
    }

    fun postPhoneNumberAuth(phoneNumber: String) {
        viewModelScope.launch {
            signUpRepository.postPhoneNumber(phoneNumber = phoneNumber).onSuccess {
                _signUpState.update {
                    it.copy(
                        isSuccessRequestPhoneNumber = true
                    )
                }
            }.onFailure { error ->
                Log.d("phone", error.message.toString())
            }
        }
    }

    fun postVerifyNumberAuth(
        phoneNumber: String,
        verifyNumber: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit,
    ) {
        viewModelScope.launch {
            signUpRepository.postVerifyNumber(
                phoneNumber = phoneNumber,
                verifyNumber = verifyNumber,
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
                _signUpState.update {
                    it.copy(
                        authNumberErrorMessage = errorMessage,
                        isAuthNumberError = true
                    )
                }
                onError(errorMessage)
            }
        }
        // TODO - 성공 / 실패 분기처리 -> 버튼 색상 등등
    }

    fun getRegionInfo() {
        viewModelScope.launch {
            if (_signUpState.value.regionList.regions.isEmpty()) {
                signUpRepository.getRegionList().onSuccess { regionList ->
                    _signUpState.update { current ->
                        current.copy(
                            regionList = regionList,
                            mainLocationList = regionList.regions.map { it.location },
                            subLocationList = regionList.regions.map { it.subLocations }
                        )
                    }
                }.onFailure { error ->
                    Log.d("phone", error.message.toString())
                }
            }
        }
    }

    fun postSignUp(
        phoneNumber: String = _signUpState.value.phoneNumber,
        nickname: String = _signUpState.value.nickname,
        birthYear: Int = _signUpState.value.birthYear,
        gender: Boolean = _signUpState.value.gender,
        profileImage: String = _signUpState.value.profileImage,
        region: String = _signUpState.value.region
    ) {
        viewModelScope.launch {
            signUpRepository.postSignUp(
                phoneNumber = phoneNumber,
                nickname = nickname,
                birthYear = birthYear,
                gender = gender,
                profileImage = profileImage,
                region = region
            ).onSuccess {
                Log.d("result", "성공")
            }.onFailure { error ->
                Log.d("result", error.toString())
            }
        }
    }
}

// TODO - 실패 케이스에서 상태관리