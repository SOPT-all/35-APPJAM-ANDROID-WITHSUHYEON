package com.sopt.withsuhyeon.feature.onboarding

import androidx.lifecycle.ViewModel
import com.sopt.withsuhyeon.core.util.KeyStorage.DEFAULT
import com.sopt.withsuhyeon.core.util.KeyStorage.LENGTH_ERROR
import com.sopt.withsuhyeon.core.util.KeyStorage.SPECIAL_CHARACTER_ERROR
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
) : ViewModel() {
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