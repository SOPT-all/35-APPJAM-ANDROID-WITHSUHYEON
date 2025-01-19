package com.sopt.withsuhyeon.feature.onboarding

import androidx.lifecycle.ViewModel
import com.sopt.withsuhyeon.core.util.KeyStorage.DEFAULT
import com.sopt.withsuhyeon.core.util.KeyStorage.LENGTH_ERROR
import com.sopt.withsuhyeon.core.util.KeyStorage.SPECIAL_CHARACTER_ERROR
import com.sopt.withsuhyeon.core.util.regex.containsSpecialCharacters
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
) : ViewModel() {
    fun isNicknameValid(nickname: String): String {
        return when {
            nickname.length !in 2..12 -> LENGTH_ERROR
            nickname.containsSpecialCharacters() -> SPECIAL_CHARACTER_ERROR
            else -> DEFAULT
        }
    }
}
