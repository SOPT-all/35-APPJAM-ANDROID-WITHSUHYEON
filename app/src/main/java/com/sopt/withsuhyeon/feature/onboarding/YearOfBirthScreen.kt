package com.sopt.withsuhyeon.feature.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kez.picker.rememberPickerState
import com.sopt.withsuhyeon.R
import com.sopt.withsuhyeon.core.component.button.LargeButton
import com.sopt.withsuhyeon.core.component.picker.YearPicker
import com.sopt.withsuhyeon.core.component.progressbar.AnimatedProgressBar
import com.sopt.withsuhyeon.core.util.KeyStorage.NEXT_BUTTON_TEXT
import com.sopt.withsuhyeon.core.util.time.currentDate
import com.sopt.withsuhyeon.feature.onboarding.components.OnBoardingTitle
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.colors

@Composable
fun YearOfBirthRoute(
    navigateToNext: () -> Unit,
    padding: PaddingValues
) {
    YearOfBirthScreen(
        onButtonClick = navigateToNext,
        padding = padding
    )
}

@Composable
fun YearOfBirthScreen(
    onButtonClick: () -> Unit,
    padding: PaddingValues,
    modifier: Modifier = Modifier,
    viewModel: OnBoardingViewModel = hiltViewModel()
) {
    val yearPickerState = rememberPickerState(currentDate.year)
    val selectedYear = yearPickerState.selectedItem - 1
    // TODO - 지금 2025, 2024 이런 식으로 나옴..! 바꾸기!
    Column(
        modifier = modifier
            .padding(padding)
            .padding(16.dp)
            .background(colors.White)
    ) {
        AnimatedProgressBar(progress = 0.66f)
        OnBoardingTitle(text = stringResource(R.string.onboarding_year_of_birth_title))
        Spacer(modifier = Modifier.height(100.dp))
        YearPicker()
        Spacer(modifier = Modifier.weight(1f))
        LargeButton(
            onClick = {
                viewModel.updateYear(selectedYear)
                onButtonClick()
            },
            text = NEXT_BUTTON_TEXT,
            isDisabled = false,
        )
    }
}