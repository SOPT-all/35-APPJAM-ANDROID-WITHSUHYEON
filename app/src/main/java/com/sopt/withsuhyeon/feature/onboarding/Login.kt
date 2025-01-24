package com.sopt.withsuhyeon.feature.onboarding

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sopt.withsuhyeon.R
import com.sopt.withsuhyeon.core.component.button.BasicButtonForTextField
import com.sopt.withsuhyeon.core.component.button.LargeButton
import com.sopt.withsuhyeon.core.component.textfield.BasicShortTextField
import com.sopt.withsuhyeon.core.component.topbar.MainTopNavBar
import com.sopt.withsuhyeon.core.util.KeyStorage.AFTER_SEND_BUTTON_TEXT
import com.sopt.withsuhyeon.core.util.KeyStorage.BEFORE_SEND_BUTTON_TEXT
import com.sopt.withsuhyeon.core.util.KeyStorage.EMPTY_STRING
import com.sopt.withsuhyeon.core.util.KeyStorage.NEXT_BUTTON_TEXT
import com.sopt.withsuhyeon.core.util.regex.checkValidPhoneNumber
import com.sopt.withsuhyeon.feature.onboarding.components.OnBoardingTitle
import com.sopt.withsuhyeon.feature.onboarding.viewmodel.LoginViewModel
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.colors

@Composable
fun LoginRoute(
    navigateToLoginFinish: () -> Unit,
    padding: PaddingValues,
    viewModel: LoginViewModel = hiltViewModel()
) {
    LoginScreen(
        padding = padding,
        onNavigateToLoginFinish = navigateToLoginFinish,
        viewModel = viewModel
    )
}

@Composable
fun LoginScreen(
    padding: PaddingValues,
    onNavigateToLoginFinish: () -> Unit,
    viewModel: LoginViewModel,
    modifier: Modifier = Modifier
) {
    var isPhoneNumberInputValid by remember { mutableStateOf(false) }
    var isPhoneNumberInputFocused by remember { mutableStateOf(false) }
    var isPhoneNumberAuthVisible by remember { mutableStateOf(false) }
    var isPhoneNumberAuthButtonEnabled by remember { mutableStateOf(false) }
    var phoneNumberAuthButtonText by remember { mutableStateOf(BEFORE_SEND_BUTTON_TEXT) }
    var isAuthNumberInputValid by remember { mutableStateOf(false) }
    var isAuthNumberInputFocused by remember { mutableStateOf(false) }

    var isBorderBlue by remember { mutableStateOf(true) }

    val state by viewModel.loginState.collectAsStateWithLifecycle()

    Column(
        modifier = modifier
            .background(colors.White)
            .padding(padding)
            .imePadding()
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        MainTopNavBar(
            text = EMPTY_STRING
        )
        HorizontalDivider(color = colors.Grey100)
        OnBoardingTitle(
            text = stringResource(R.string.login_title)
        )
        Spacer(modifier = Modifier.height(32.dp))
        Column(
            modifier = modifier.padding(
                horizontal = 16.dp
            )
        ) {
            BasicShortTextField(
                value = state.phoneNumber,
                hint = stringResource(R.string.login_input_hint),
                title = stringResource(R.string.login_input_title),
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
                trailingContent = {
                    BasicButtonForTextField(
                        text = phoneNumberAuthButtonText,
                        onClick = {
                            viewModel.postPhoneNumberAuth(state.phoneNumber)
                            isPhoneNumberAuthVisible = true
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
                    value = state.authNumber,
                    title = stringResource(R.string.onboarding_phone_number_auth_input_title),
                    hint = stringResource(R.string.onboarding_phone_number_auth_input_hint),
                    isValid = isBorderBlue,
                    textFieldBorderColor = if (isBorderBlue && isAuthNumberInputFocused) colors.Purple300 else if (isBorderBlue == false) colors.Red01 else colors.Grey100,
                    onFocusChange = {
                        isAuthNumberInputFocused = it
                    },
                    onValueChange = { input ->
                        if (!isAuthNumberInputValid) {
                            viewModel.refreshErrorMessage()
                            isBorderBlue = true
                        }
                        if (input.length <= 6) {
                            isAuthNumberInputValid = input.length == 6
                            viewModel.updateAuthNumber(input)
                        }
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number
                    ),
                    maxLength = 6,
                    errorMessage = state.authNumberErrorMessage
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))
        HorizontalDivider(
            modifier = Modifier.height(1.dp),
            color = colors.Grey100
        )
        Spacer(modifier = Modifier.height(16.dp))

        LargeButton(
            onClick = {
                viewModel.postVerifyNumberAuth(
                    state.phoneNumber,
                    state.authNumber,
                    onSuccess = {
                        onNavigateToLoginFinish()
                    },
                    onError = {
                        isAuthNumberInputValid = false
                        viewModel.updateAuthNumber(EMPTY_STRING)
                        isBorderBlue = false
                    }
                )
            },
            text = NEXT_BUTTON_TEXT,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
            isDisabled = !isAuthNumberInputValid
        )
    }
}