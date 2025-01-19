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
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.colors

@Composable
fun PhoneNumberAuthenticationRoute(
    navigateToNext: () -> Unit,
    viewModel: OnBoardingViewModel = hiltViewModel()
) {
    PhoneNumberAuthenticationScreen(
        onButtonClick = navigateToNext
    )
}

@Composable
fun PhoneNumberAuthenticationScreen(
    onButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var phoneNumberValue by remember { mutableStateOf(EMPTY_STRING) }
    var isPhoneNumberInputValid by remember { mutableStateOf(false) }
    var isPhoneNumberInputFocused by remember { mutableStateOf(false) }
    var isPhoneNumberAuthVisible by remember { mutableStateOf(false) }
    var isPhoneNumberAuthButtonEnabled by remember { mutableStateOf(false) }
    var phoneNumberAuthButtonText by remember { mutableStateOf(BEFORE_SEND_BUTTON_TEXT) }
    var authNumberValue by remember { mutableStateOf(EMPTY_STRING) }
    var isAuthNumberInputValid by remember { mutableStateOf(false) }
    var isAuthNumberInputFocused by remember { mutableStateOf(false) }

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
        AnimatedProgressBar(progress = 0.33f)
        Spacer(
            modifier = Modifier.height(16.dp)
        )
        OnBoardingTitle(text = stringResource(R.string.onboarding_phone_number_title))
        Spacer(
            modifier = Modifier.height(32.dp)
        )
        BasicShortTextField(
            value = phoneNumberValue,
            title = stringResource(R.string.onboarding_phone_number_input_title),
            hint = stringResource(R.string.onboarding_phone_number_input_hint),
            isValid = isPhoneNumberInputValid,
            onFocusChange = {
                isPhoneNumberInputFocused = it
            },
            onValueChange = { input ->
                isPhoneNumberInputValid = input.length == 11
                isPhoneNumberAuthButtonEnabled = input.length == 11
                phoneNumberValue = input
            },
            maxLength = 11,
            trailingContent = {
                BasicButtonForTextField(
                    text = phoneNumberAuthButtonText,
                    onClick = {
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

        HorizontalDivider(
            modifier = Modifier.height(1.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        LargeButton(
            onClick = onButtonClick,
            text = NEXT_BUTTON_TEXT,
            isDisabled = !isAuthNumberInputValid
        )
    }
}
