package com.sopt.withsuhyeon.feature.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.sopt.withsuhyeon.R
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.colors
import kotlinx.coroutines.delay

@Composable
fun SplashRoute(
    onSplashCompleted: () -> Unit,
    padding: PaddingValues
) {
    SplashScreen(
        onSplashCompleted = onSplashCompleted,
        padding = padding
    )
}

@Composable
fun SplashScreen(
    onSplashCompleted: () -> Unit,
    padding: PaddingValues,
    modifier: Modifier = Modifier
) {
    LaunchedEffect(Unit) {
        delay(2500)
        onSplashCompleted() // 콜백 호출
    }

    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.splash)
    )
    val progress by animateLottieCompositionAsState(
        composition = composition,
        isPlaying = true,
    )
    Column(
        modifier = modifier
            .background(color = colors.White)
            .padding(padding)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        LottieAnimation(
            composition = composition,
            progress = { progress },
            modifier = Modifier.scale(1.5f)
        )
    }
}
