package com.sopt.withsuhyeon.feature.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sopt.withsuhyeon.R
import com.sopt.withsuhyeon.core.component.button.BasicButtonForTextField
import com.sopt.withsuhyeon.core.component.button.LargeButton
import com.sopt.withsuhyeon.core.component.progressbar.AnimatedProgressBar
import com.sopt.withsuhyeon.core.component.textfield.BasicShortTextField
import com.sopt.withsuhyeon.core.component.topbar.MainTopNavBar
import com.sopt.withsuhyeon.core.util.KeyStorage.AFTER_SEND_BUTTON_TEXT
import com.sopt.withsuhyeon.core.util.KeyStorage.BEFORE_SEND_BUTTON_TEXT
import com.sopt.withsuhyeon.core.util.KeyStorage.EMPTY_STRING
import com.sopt.withsuhyeon.core.util.KeyStorage.NEXT_BUTTON_TEXT
import com.sopt.withsuhyeon.feature.onboarding.components.OnBoardingTitle
import com.sopt.withsuhyeon.feature.onboarding.viewmodel.SignUpViewModel
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.colors

@Composable
fun PhoneNumberAuthenticationRoute(
    navigateToNext: () -> Unit,
    padding: PaddingValues,
    viewModel: SignUpViewModel
) {
    PhoneNumberAuthenticationScreen(
        onButtonClick = navigateToNext,
        padding = padding,
        viewModel = viewModel,
    )
}

@Composable
fun PhoneNumberAuthenticationScreen(
    onButtonClick: () -> Unit,
    padding: PaddingValues,
    viewModel: SignUpViewModel,
    modifier: Modifier = Modifier
) {
    var isPhoneNumberInputValid by remember { mutableStateOf(false) }
    var isPhoneNumberInputFocused by remember { mutableStateOf(false) }
    var isPhoneNumberAuthVisible by remember { mutableStateOf(false) }
    var isPhoneNumberAuthButtonEnabled by remember { mutableStateOf(false) }
    var phoneNumberAuthButtonText by remember { mutableStateOf(BEFORE_SEND_BUTTON_TEXT) }
    var authNumberValue by remember { mutableStateOf("") }
    var isAuthNumberInputValid by remember { mutableStateOf(false) }
    var isAuthNumberInputFocused by remember { mutableStateOf(false) }
    val state by viewModel.signUpState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.updateProgress(state.progress + 1f / 7)
    }

    Column(
        modifier = modifier
            .background(color = colors.White)
            .padding(padding)
            .fillMaxSize()
    ) {
        MainTopNavBar(text = EMPTY_STRING)
        HorizontalDivider(
            modifier = Modifier.height(1.dp),
            color = colors.Grey100
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 16.dp)
        ) {
            AnimatedProgressBar(
                progress = state.progress,
                modifier = Modifier.padding(top = 16.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            OnBoardingTitle(text = stringResource(R.string.onboarding_phone_number_title))
            Spacer(modifier = Modifier.height(32.dp))
            BasicShortTextField(
                value = state.phoneNumber,
                title = stringResource(R.string.onboarding_phone_number_input_title),
                hint = stringResource(R.string.onboarding_phone_number_input_hint),
                isValid = isPhoneNumberInputValid,
                onFocusChange = {
                    isPhoneNumberInputFocused = it
                },
                onValueChange = { input ->
                    isPhoneNumberInputValid = input.length == 11
                    isPhoneNumberAuthButtonEnabled = input.length == 11
                    viewModel.updatePhoneNumber(input)
                },
                maxLength = 11,
                trailingContent = {
                    BasicButtonForTextField(
                        text = phoneNumberAuthButtonText,
                        onClick = {
                            isPhoneNumberAuthVisible = true
                            viewModel.postPhoneNumberAuth(state.phoneNumber)
                            isPhoneNumberAuthButtonEnabled = false
                            phoneNumberAuthButtonText = AFTER_SEND_BUTTON_TEXT
                        },
                        modifier = Modifier,
                        enabled = isPhoneNumberAuthButtonEnabled
                    )
                }
            )
            Spacer(
                modifier = Modifier.height(12.dp)
            )
            if (isPhoneNumberAuthVisible) {
                BasicShortTextField(
                    value = authNumberValue,
                    title = stringResource(R.string.onboarding_phone_number_auth_input_title),
                    hint = stringResource(R.string.onboarding_phone_number_auth_input_hint),
                    isValid = isAuthNumberInputValid,
                    onFocusChange = {
                        isAuthNumberInputFocused = it
                    },
                    onValueChange = { input ->
                        isAuthNumberInputValid = input.length == 6
                        authNumberValue = input
                    },
                    maxLength = 6,
                )
            }
            Spacer(modifier = Modifier.weight(1f))
        }
        HorizontalDivider(
            modifier = Modifier.height(1.dp),
            color = colors.Grey100
        )
        Spacer(modifier = Modifier.height(16.dp))

        LargeButton(
            onClick = {
                viewModel.postVerifyNumberAuth(
                    phoneNumber = state.phoneNumber,
                    verifyNumber = authNumberValue
                )
                onButtonClick()
            },
            text = NEXT_BUTTON_TEXT,
            modifier = Modifier.padding(horizontal = 16.dp),
            isDisabled = !isAuthNumberInputValid
        )
    }
}