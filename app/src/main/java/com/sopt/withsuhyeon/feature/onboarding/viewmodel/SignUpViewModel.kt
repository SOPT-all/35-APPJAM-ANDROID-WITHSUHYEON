package com.sopt.withsuhyeon.feature.onboarding.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.withsuhyeon.core.util.KeyStorage.DEFAULT
import com.sopt.withsuhyeon.core.util.KeyStorage.LENGTH_ERROR
import com.sopt.withsuhyeon.core.util.KeyStorage.SPECIAL_CHARACTER_ERROR
import com.sopt.withsuhyeon.domain.repository.SignUpRepository
import com.sopt.withsuhyeon.feature.onboarding.state.SignUpState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
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

    fun updateNickname(nickname: String) {
        _signUpState.update {
            it.copy(
                nickname = nickname,
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

    fun isNicknameValid(nickname: String): String {
        if (nickname.length !in 2..12) return LENGTH_ERROR
        if (containsSpecialCharacters(nickname)) return SPECIAL_CHARACTER_ERROR
        return DEFAULT
    }

    private fun containsSpecialCharacters(input: String): Boolean {
        val specialCharactersRegex = "[^a-zA-Z가-힣ㄱ-ㅎㅏ-ㅣ0-9\\s]".toRegex()
        return specialCharactersRegex.containsMatchIn(input)
    }

    fun postPhoneNumberAuth(phoneNumber: String) {
        viewModelScope.launch {
            signUpRepository.postPhoneNumber(phoneNumber = phoneNumber).onSuccess {
                Log.d("phone", "성공")
            }.onFailure { error ->
                Log.d("phone", error.message.toString())
            }
        }
    }

    fun postVerifyNumberAuth(phoneNumber: String, verifyNumber: String) {
        viewModelScope.launch {
            signUpRepository.postVerifyNumber(
                phoneNumber = phoneNumber,
                verifyNumber = verifyNumber
            ).onSuccess {
                Log.d("phone", "성공")
            }.onFailure { error ->
                Log.d("phone", error.message.toString())
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
            Log.d(
                "test",
                "${phoneNumber} ${nickname} ${birthYear} ${gender} ${profileImage} ${region}"
            )
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
                Log.d("result", error.message.toString())
            }
        }
    }

}