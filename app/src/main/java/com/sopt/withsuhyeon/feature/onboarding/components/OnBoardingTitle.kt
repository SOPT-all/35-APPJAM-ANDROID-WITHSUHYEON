package com.sopt.withsuhyeon.feature.onboarding.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.typography

@Composable
fun OnBoardingTitle(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier.padding(vertical = 20.dp, horizontal = 16.dp),
        text = text,
        style = typography.title02_B,
    )
}