package com.sopt.withsuhyeon.feature.onboarding.viewmodel

import androidx.lifecycle.ViewModel
import com.sopt.withsuhyeon.core.util.KeyStorage.DEFAULT
import com.sopt.withsuhyeon.core.util.KeyStorage.LENGTH_ERROR
import com.sopt.withsuhyeon.core.util.KeyStorage.SPECIAL_CHARACTER_ERROR
import com.sopt.withsuhyeon.feature.onboarding.state.SignUpState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(

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

    fun updateRegion(region: String?) {
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
}