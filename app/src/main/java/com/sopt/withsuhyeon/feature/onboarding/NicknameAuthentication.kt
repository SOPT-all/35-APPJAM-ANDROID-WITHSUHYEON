package com.sopt.withsuhyeon.feature.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
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
import com.sopt.withsuhyeon.core.component.topbar.MainTopNavBar
import com.sopt.withsuhyeon.core.util.KeyStorage.DEFAULT
import com.sopt.withsuhyeon.core.util.KeyStorage.EMPTY_STRING
import com.sopt.withsuhyeon.core.util.KeyStorage.LENGTH_ERROR
import com.sopt.withsuhyeon.core.util.KeyStorage.LENGTH_ERROR_MESSAGE
import com.sopt.withsuhyeon.core.util.KeyStorage.NEXT_BUTTON_TEXT
import com.sopt.withsuhyeon.core.util.KeyStorage.SPECIAL_CHARACTER_ERROR
import com.sopt.withsuhyeon.core.util.KeyStorage.SPECIAL_CHARACTER_ERROR_MESSAGE
import com.sopt.withsuhyeon.feature.onboarding.components.OnBoardingTitle
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.colors

@Composable
fun NickNameAuthenticationRoute(
    navigateToNext: () -> Unit,
) {
    NickNameAuthenticationScreen(
        onButtonClick = navigateToNext
    )
}

@Composable
fun NickNameAuthenticationScreen(
    onButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: OnBoardingViewModel = hiltViewModel()
) {
    var nicknameValue by remember { mutableStateOf(EMPTY_STRING) }
    var isNicknameValid by remember { mutableStateOf(false) }
    var nicknameErrorType by remember { mutableStateOf(LENGTH_ERROR) }
    var isNicknameTextFiledFocused by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf(EMPTY_STRING) }

    fun updateErrorMessage(validationState: String) {
        errorMessage = when (validationState) {
            LENGTH_ERROR -> LENGTH_ERROR_MESSAGE
            SPECIAL_CHARACTER_ERROR -> SPECIAL_CHARACTER_ERROR_MESSAGE
            else -> EMPTY_STRING
        }
    }

    Column(
        modifier = modifier
            .background(color = colors.White)
            .padding(
                vertical = 16.dp,
                horizontal = 16.dp
            )
            .fillMaxSize()

    ) {
        MainTopNavBar(
            text = EMPTY_STRING,
            modifier = Modifier.padding(20.dp)
        )
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
                nicknameValue = input
                nicknameErrorType = viewModel.isNicknameValid(input)
                updateErrorMessage(nicknameErrorType)
                isNicknameValid = nicknameErrorType == DEFAULT
            },
            maxLength = 12,
            errorMessage = errorMessage
        )
        Spacer(modifier = Modifier.weight(1f))

        HorizontalDivider(
            modifier = Modifier.height(1.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        LargeButton(
            onClick = onButtonClick,
            text = NEXT_BUTTON_TEXT,
            isDisabled = nicknameErrorType != DEFAULT,
        )
    }
}