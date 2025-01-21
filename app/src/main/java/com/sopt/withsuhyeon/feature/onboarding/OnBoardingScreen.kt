package com.sopt.withsuhyeon.feature.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sopt.withsuhyeon.R
import com.sopt.withsuhyeon.core.component.button.LargeButton
import com.sopt.withsuhyeon.core.util.modifier.noRippleClickable
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.colors
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.typography

@Composable
fun OnBoardingRoute(
    padding: PaddingValues,
    navigateToSignUp: () -> Unit,
    navigateToLogin: () -> Unit,
    viewModel: OnBoardingViewModel = hiltViewModel()
) {
    OnBoardingScreen(
        padding = padding,
        onSignUpButtonClick = navigateToSignUp,
        onLoginButtonClick = navigateToLogin
    )
}

@Composable
fun OnBoardingScreen(
    padding: PaddingValues,
    onSignUpButtonClick: () -> Unit,
    onLoginButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(color = colors.White)
            .padding(padding)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.8f)
                .aspectRatio(0.8f)
                .background(color = colors.Grey400)
        )
        Column(
            modifier = Modifier
                .padding(
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 46.dp
                )
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LargeButton(
                text = stringResource(R.string.onboarding_start_text),
                onClick = onSignUpButtonClick
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier
                    .padding(
                        vertical = 10.dp
                    ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.Start),
            ) {
                Text(
                    text = stringResource(R.string.onboarding_account_question),
                    style = typography.body02_SB.merge(color = colors.Grey400)
                )
                Text(
                    text = stringResource(R.string.onboarding_login_text),
                    style = typography.body02_SB.merge(color = colors.Purple600),
                    modifier = Modifier.noRippleClickable(onLoginButtonClick)
                )
            }
        }
    }
}