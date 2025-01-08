package com.sopt.withsuhyeon.feature.onboarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.sopt.withsuhyeon.feature.onboarding.contract.OnBoardingContract
import kotlinx.coroutines.flow.collectLatest

@Composable
fun OnBoardingRoute(
    padding: PaddingValues,
    navigateToHome: () -> Unit,
    viewModel: OnBoardingViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.sideEffect.collectLatest { effect ->
            when (effect) {
                OnBoardingContract.OnBoardingSideEffect.NavigateToHome -> {
                    navigateToHome()
                }
            }
        }
    }
    OnBoardingScreen(
        padding = padding,
        onButtonClick = {
            viewModel.setEvent(OnBoardingContract.OnBoardingEvent.EndLoading)
        }
    )
}
@Composable
fun OnBoardingScreen(
    padding: PaddingValues,
    onButtonClick: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize()
            .padding(padding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = onButtonClick,
        ) {
            Text(
                text = "Go To Home"
            )
        }
    }
}