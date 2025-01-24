package com.sopt.withsuhyeon.feature.onboarding

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
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
import com.sopt.withsuhyeon.core.util.regex.checkValidPhoneNumber
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
    var isPhoneNumberAuthButtonEnabled by remember { mutableStateOf(false) }
    var phoneNumberAuthButtonText by remember { mutableStateOf(BEFORE_SEND_BUTTON_TEXT) }
    var authNumberValue by remember { mutableStateOf("") }
    var isAuthNumberInputValid by remember { mutableStateOf(false) }
    var isAuthNumberInputFocused by remember { mutableStateOf(false) }
//    var isAuthNumberError by remember { mutableStateOf(false) }
//    var authErrorMessage by remember { mutableStateOf(EMPTY_STRING) }
    val state by viewModel.signUpState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.updateProgress(2f / 7)
        viewModel.updatePhoneNumber(EMPTY_STRING)
        viewModel.updateRequestPhoneNumberState(false)
    }

    Column(
        modifier = modifier
            .background(color = colors.White)
            .padding(padding)
            .imePadding()
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
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
                isValid = true,
                onFocusChange = {
                    isPhoneNumberInputFocused = it
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                ),
                onValueChange = { input ->
                    if (input.length <= 11) {
                        isPhoneNumberInputValid =
                            if (input.length == 11) input.checkValidPhoneNumber() else false
                        isPhoneNumberAuthButtonEnabled = isPhoneNumberInputValid
                        viewModel.updatePhoneNumber(input)
                    }
                },
                maxLength = 11,
                modifier = Modifier
                    .height(84.dp),
                trailingContent = {
                    BasicButtonForTextField(
                        text = phoneNumberAuthButtonText,
                        onClick = {
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
            if (state.isSuccessRequestPhoneNumber) {
                BasicShortTextField(
                    value = authNumberValue,
                    title = stringResource(R.string.onboarding_phone_number_auth_input_title),
                    hint = stringResource(R.string.onboarding_phone_number_auth_input_hint),
                    isValid = !state.isAuthNumberError,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number
                    ),
                    onFocusChange = {
                        isAuthNumberInputFocused = it
                    },
                    onValueChange = { input ->
                        if(input.length <= 6) {
                            isAuthNumberInputValid = input.length == 6
                            authNumberValue = input
                        }
                    },
                    modifier = Modifier
                        .height(112.dp),
                    maxLength = 6,
                    errorMessage = state.authNumberErrorMessage,
                )
            }
        }
        Column {
            HorizontalDivider(
                modifier = Modifier.height(1.dp),
                color = colors.Grey100
            )
            Spacer(modifier = Modifier.height(16.dp))

            LargeButton(
                onClick = {
                    viewModel.postVerifyNumberAuth(
                        phoneNumber = state.phoneNumber,
                        verifyNumber = authNumberValue,
                        onSuccess = {
                            onButtonClick()
                        },
                        onError = { isAuthNumberInputValid = false }
                    )
                },
                text = NEXT_BUTTON_TEXT,
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                isDisabled = !isAuthNumberInputValid
            )
        }
    }
}