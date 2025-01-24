package com.sopt.withsuhyeon.feature.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
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

    var isPlaying by remember { mutableStateOf(false) }

    var selectedIndex by remember { mutableIntStateOf(0) }

    isPlaying = false

    val lottieFiles = listOf(
        R.raw.function_1,
        R.raw.function_2,
        R.raw.function_3
    )

    var dragDirection by remember { mutableIntStateOf(0) }

    Box(
        modifier = modifier.fillMaxSize(),
    ) {
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

                    if (selectedIndex == 0) {
                        LottieAnimation(
                            composition = firstLottie,
                            iterations = 1,
                            modifier = Modifier
                                .fillMaxWidth(),
                            contentScale = ContentScale.Crop,
                            speed = 1f
                        )
                    }

                    if (selectedIndex == 1) {
                        LottieAnimation(
                            composition = secondLottie,
                            iterations = 1,
                            modifier = Modifier
                                .fillMaxWidth(),
                            contentScale = ContentScale.Crop,
                            speed = 1f
                        )
                    }

                    if (selectedIndex == 2) {
                        LottieAnimation(
                            composition = thirdLottie,
                            iterations = 1,
                            modifier = Modifier
                                .fillMaxWidth(),
                            contentScale = ContentScale.Crop,
                            speed = 1f
                        )
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                top = 100.dp,
                                start = 16.dp,
                                end = 16.dp
                            )
                    ) {
                        Text(
                            text = when (selectedIndex) {
                                0 -> stringResource(R.string.onboarding_lottie_main_title_1)
                                1 -> stringResource(R.string.onboarding_lottie_main_title_2)
                                else -> stringResource(R.string.onboarding_lottie_main_title_3)
                            },
                            style = typography.title01_B,
                        )
                        Spacer(Modifier.height(16.dp))
                        Text(
                            text = when (selectedIndex) {
                                0 -> stringResource(R.string.onboarding_lottie_sub_title_1)
                                1 -> stringResource(R.string.onboarding_lottie_sub_title_2)
                                else -> stringResource(R.string.onboarding_lottie_sub_title_3)
                            },
                            style = typography.body02_B.merge(color = colors.Grey500)
                        )
                    }


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
                        bottom = 14.dp
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
}

@Preview
@Composable
fun PreviewOnBoardingScreen() {
    OnBoardingScreen(
        padding = PaddingValues(0.dp),
        onSignUpButtonClick = { },
        onLoginButtonClick = { }
    )
}