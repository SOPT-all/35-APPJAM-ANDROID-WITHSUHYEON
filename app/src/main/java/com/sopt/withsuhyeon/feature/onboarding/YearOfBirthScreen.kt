package com.sopt.withsuhyeon.feature.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sopt.withsuhyeon.R
import com.sopt.withsuhyeon.core.component.button.LargeButton
import com.sopt.withsuhyeon.core.component.picker.YearPicker
import com.sopt.withsuhyeon.core.component.progressbar.AnimatedProgressBar
import com.sopt.withsuhyeon.core.util.KeyStorage.NEXT_BUTTON_TEXT
import com.sopt.withsuhyeon.feature.onboarding.components.OnBoardingTitle
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.colors

@Composable
fun YearOfBirthRoute(
    navigateToNext: () -> Unit
) {
    YearOfBirthScreen(
        onButtonClick = navigateToNext
    )
}

@Composable
fun YearOfBirthScreen(
    onButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: OnBoardingViewModel = hiltViewModel()
) {
    Column(
        modifier = modifier
            .padding(16.dp)
            .background(colors.White)
    ) {
        AnimatedProgressBar(progress = 0.66f)
        OnBoardingTitle(text = stringResource(R.string.onboarding_year_of_birth_title))
        Spacer(modifier = Modifier.height(100.dp))
        YearPicker()
        Spacer(modifier = Modifier.weight(1f))
        LargeButton(
            onClick = onButtonClick,
            text = NEXT_BUTTON_TEXT,
            isDisabled = false,
        )
    }
}