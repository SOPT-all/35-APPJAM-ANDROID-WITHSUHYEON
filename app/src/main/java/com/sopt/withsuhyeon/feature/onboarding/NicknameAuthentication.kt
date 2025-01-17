package com.sopt.withsuhyeon.feature.onboarding

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sopt.withsuhyeon.R
import com.sopt.withsuhyeon.core.component.button.LargeButton
import com.sopt.withsuhyeon.core.component.progressbar.AnimatedProgressBar
import com.sopt.withsuhyeon.core.component.textfield.BasicShortTextField
import com.sopt.withsuhyeon.core.util.KeyStorage.NEXT_BUTTON_TEXT
import com.sopt.withsuhyeon.feature.onboarding.components.OnBoardingTitle

@Composable
fun NickNameAuthenticationRoute(
    navigateToNext: () -> Unit,
    viewModel: OnBoardingViewModel = hiltViewModel()
) {
    NickNameAuthenticationScreen(
        onButtonClick = navigateToNext
    )
}

@Composable
fun NickNameAuthenticationScreen(
    onButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var nicknameValue by remember { mutableStateOf("") }
    var isNicknameValid by remember { mutableStateOf(false) }
    var isNicknameTextFiledFocused by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
            .fillMaxHeight(),
    ) {
        AnimatedProgressBar(progress = 0.5f)
        Spacer(
            modifier = Modifier.height(16.dp)
        )
        OnBoardingTitle(text = stringResource(R.string.onboarding_nickname_screen_title))
        Spacer(
            modifier = Modifier.height(32.dp)
        )
        BasicShortTextField(
            value = nicknameValue,
            title = stringResource(R.string.onboarding_nickname_input_title),
            hint = stringResource(R.string.onboarding_nickname_input_hint),
            isValid = isNicknameValid,
            onFocusChange = {
                isNicknameTextFiledFocused = it
            },
            onValueChange = { input ->
                isNicknameValid = input.length in 2..12
                nicknameValue = input
            },
            maxLength = 12,
            errorMessage = stringResource(R.string.onboarding_nickname_max_input_error_message),
        )
        // TODO - 특수기호나 각종 에러 케이스 뷰모델 구현
        Spacer(modifier = Modifier.weight(1f))
        LargeButton(
            onClick = onButtonClick,
            text = NEXT_BUTTON_TEXT,
            isDisabled = !isNicknameValid,
        )
    }
}