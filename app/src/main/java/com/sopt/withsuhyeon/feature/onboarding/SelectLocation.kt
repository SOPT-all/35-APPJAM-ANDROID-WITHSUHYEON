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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sopt.withsuhyeon.R
import com.sopt.withsuhyeon.core.component.button.LargeButton
import com.sopt.withsuhyeon.core.component.chip.MainLocationChip
import com.sopt.withsuhyeon.core.component.menu.LocationMenu
import com.sopt.withsuhyeon.core.component.progressbar.AnimatedProgressBar
import com.sopt.withsuhyeon.core.component.topbar.MainTopNavBar
import com.sopt.withsuhyeon.core.util.KeyStorage.EMPTY_STRING
import com.sopt.withsuhyeon.core.util.KeyStorage.NEXT_BUTTON_TEXT
import com.sopt.withsuhyeon.feature.onboarding.components.OnBoardingTitle
import com.sopt.withsuhyeon.feature.onboarding.viewmodel.SignUpViewModel
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.colors

@Composable
fun SelectLocationRoute(
    padding: PaddingValues,
    navigateToNext: () -> Unit,
    viewModel: SignUpViewModel
) {
    SelectLocationScreen(
        padding = padding,
        onButtonClick = navigateToNext,
        viewModel
    )
}

@Composable
fun SelectLocationScreen(
    padding: PaddingValues,
    onButtonClick: () -> Unit,
    viewModel: SignUpViewModel,
    modifier: Modifier = Modifier,
) {
    val selectedMainLocation by remember { mutableStateOf("") }
    val selectedSubLocation by remember { mutableStateOf("") }

    val state by viewModel.signUpState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.updateProgress(1f)
        viewModel.getRegionInfo()
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
            OnBoardingTitle(text = stringResource(R.string.onboarding_location_select_title))
            LocationMenu(
                mainLocationList = state.mainLocationList,
                subLocationList = state.subLocationList,
                selectedMainLocation = selectedMainLocation,
                selectedSubLocation = selectedSubLocation,
                onSubLocationSelect = { region ->
                    viewModel.updateRegion(region ?: EMPTY_STRING)
                }
            )
        }
        HorizontalDivider(modifier = Modifier.height(1.dp))
        Spacer(modifier = Modifier.height(16.dp))
        LargeButton(
            onClick = {
                onButtonClick()
            },
            text = NEXT_BUTTON_TEXT,
            modifier = Modifier.padding(horizontal = 16.dp),
        )
    }
}