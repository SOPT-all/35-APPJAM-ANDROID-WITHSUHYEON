package com.sopt.withsuhyeon.ui.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

object WithSuhyeonTheme {
    val colors: WithSuhyeonColors
        @Composable
        @ReadOnlyComposable
        get() = LocalWithSuhyeonColorsProvider.current

    val typography: WithSuhyeonTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalWithSuhyeonTypographyProvider.current
}

@Composable
fun ProvideWithSuhyeonColorsAndTypography(
    colors: WithSuhyeonColors,
    typography: WithSuhyeonTypography,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalWithSuhyeonColorsProvider provides colors,
        LocalWithSuhyeonTypographyProvider provides typography,
        content = content
    )
}

@Composable
fun WithSuhyeonTheme(
    content: @Composable () -> Unit
) {
    ProvideWithSuhyeonColorsAndTypography (
        colors = defaultWithSuhyeonColors,
        typography = defaultWithSuhyeonTypography
    ) {
        val view = LocalView.current
        if (!view.isInEditMode) {
            SideEffect {
                (view.context as Activity).window.run {
                    WindowCompat.getInsetsController(this, view).isAppearanceLightStatusBars = false
                }
            }
        }
        MaterialTheme(
            content = content
        )
    }
}