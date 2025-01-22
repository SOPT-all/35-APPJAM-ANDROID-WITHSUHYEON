package com.sopt.withsuhyeon.feature.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import com.sopt.withsuhyeon.R
import com.sopt.withsuhyeon.core.component.button.BasicButtonForTextField
import com.sopt.withsuhyeon.core.component.button.LargeButton
import com.sopt.withsuhyeon.core.component.textfield.BasicShortTextField
import com.sopt.withsuhyeon.core.component.topbar.MainTopNavBar
import com.sopt.withsuhyeon.core.util.KeyStorage.AFTER_SEND_BUTTON_TEXT
import com.sopt.withsuhyeon.core.util.KeyStorage.BEFORE_SEND_BUTTON_TEXT
import com.sopt.withsuhyeon.core.util.KeyStorage.EMPTY_STRING
import com.sopt.withsuhyeon.core.util.KeyStorage.NEXT_BUTTON_TEXT
import com.sopt.withsuhyeon.feature.onboarding.components.OnBoardingTitle
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.colors

@Composable
fun LoginRoute(
    navigateToLoginFinish: () -> Unit,
    padding: PaddingValues
) {
    LoginScreen(
        padding = padding,
        onNavigateToLoginFinish = navigateToLoginFinish
    )
}

@Composable
fun LoginScreen(
    padding: PaddingValues,
    onNavigateToLoginFinish: () -> Unit,
    modifier: Modifier = Modifier
) {
    var phoneNumberValue by remember { mutableStateOf("") }
    var isPhoneNumberInputValid by remember { mutableStateOf(false) }
    var isPhoneNumberInputFocused by remember { mutableStateOf(false) }
    var isPhoneNumberAuthVisible by remember { mutableStateOf(false) }
    var isPhoneNumberAuthButtonEnabled by remember { mutableStateOf(false) }
    var phoneNumberAuthButtonText by remember { mutableStateOf(BEFORE_SEND_BUTTON_TEXT) }
    var authNumberValue by remember { mutableStateOf("") }
    var isAuthNumberInputValid by remember { mutableStateOf(false) }
    var isAuthNumberInputFocused by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .background(colors.White)
            .padding(padding)
            .fillMaxSize()
    ) {
        MainTopNavBar(
            text = EMPTY_STRING
        )
        HorizontalDivider(color = colors.Grey100)
        OnBoardingTitle(
            text = stringResource(R.string.login_title)
        )
        Spacer(modifier = Modifier.height(32.dp))
        Column (
            modifier = modifier.padding(
                horizontal = 16.dp
            )
        ) {
            BasicShortTextField(
                value = phoneNumberValue,
                hint = stringResource(R.string.login_input_hint),
                title = stringResource(R.string.login_input_title),
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
        }

        Spacer(modifier = Modifier.weight(1f))
        HorizontalDivider(
            modifier = Modifier.height(1.dp),
            color = colors.Grey100
        )
        Spacer(modifier = Modifier.height(16.dp))

        LargeButton(
            onClick = onNavigateToLoginFinish,
            text = NEXT_BUTTON_TEXT,
            modifier = Modifier.padding(horizontal = 16.dp),
            isDisabled = !isAuthNumberInputValid
        )
    }

}