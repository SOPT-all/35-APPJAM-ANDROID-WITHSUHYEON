package com.sopt.withsuhyeon.feature.onboarding

import android.util.Log
import androidx.lifecycle.ViewModel
import com.sopt.withsuhyeon.core.util.KeyStorage.DEFAULT
import com.sopt.withsuhyeon.core.util.KeyStorage.LENGTH_ERROR
import com.sopt.withsuhyeon.core.util.KeyStorage.SPECIAL_CHARACTER_ERROR
import com.sopt.withsuhyeon.feature.onboarding.state.GenderState
import com.sopt.withsuhyeon.feature.onboarding.state.YearOfBirthState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
) : ViewModel() {

    private val _yearState = MutableStateFlow(YearOfBirthState())
    val yearState = _yearState.asStateFlow()
    private val _gender = MutableStateFlow(GenderState())
    val gender = _gender.asStateFlow()

    fun updateYear(newYear: Int) {
        _yearState.update { currentState ->
            currentState.copy(
                year = newYear
            )
        }
        Log.e("here", _yearState.value.year.toString())
    }

    fun updateGender(gender: String) {
        _gender.update { state ->
            state.copy(
                gender = gender
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