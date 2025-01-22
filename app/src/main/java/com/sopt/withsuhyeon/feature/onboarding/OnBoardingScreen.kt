package com.sopt.withsuhyeon.feature.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.sopt.withsuhyeon.R
import com.sopt.withsuhyeon.core.component.button.LargeButton
import com.sopt.withsuhyeon.core.util.modifier.noRippleClickable
import com.sopt.withsuhyeon.feature.onboarding.components.PageIndicator
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
    val lottieFiles = listOf(
        R.raw.function_1,
        R.raw.function_2,
        R.raw.function_3
    )

    var selectedIndex by remember { mutableIntStateOf(0) }
    var dragDirection by remember { mutableIntStateOf(0) }


    Column(
        modifier = modifier
            .background(color = colors.White)
            .padding(padding)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(42.dp)
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(28.dp)

        ) {
            Box(
                modifier = modifier
                    .fillMaxWidth()

                    .pointerInput(Unit) {
                        detectHorizontalDragGestures(
                            onHorizontalDrag = { _, dragAmount ->
                                if (dragAmount > 0) {
                                    dragDirection = 1
                                } else if (dragAmount < -0) {
                                    dragDirection = -1
                                }
                            },
                            onDragEnd = {
                                when (dragDirection) {
                                    1 -> {
                                        if (selectedIndex > 0) {
                                            selectedIndex -= 1
                                        }
                                    }

                                    -1 -> {
                                        if (selectedIndex < 2) {
                                            selectedIndex += 1
                                        }
                                    }
                                }
                                dragDirection = 0
                            }
                        )
                    }
            ) {
                val firstLottie by rememberLottieComposition(
                    LottieCompositionSpec.RawRes(
                        lottieFiles[0]
                    )
                )

                val secondLottie by rememberLottieComposition(
                    LottieCompositionSpec.RawRes(
                        lottieFiles[1]
                    )
                )

                val thirdLottie by rememberLottieComposition(
                    LottieCompositionSpec.RawRes(
                        lottieFiles[2]
                    )
                )

                LottieAnimation(
                    composition = when (selectedIndex) {
                        0 -> firstLottie
                        1 -> secondLottie
                        else -> thirdLottie
                    },
                    iterations = LottieConstants.IterateForever,
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentScale = ContentScale.Crop,
                    speed = 1.2f
                )
            }
        }
        PageIndicator(
            pageCount = 3,
            currentPage = selectedIndex,
            indicatorSize = 8.dp,
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