package com.sopt.withsuhyeon.feature.main

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.isImeVisible
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.sopt.withsuhyeon.feature.main.component.MainBottomBar
import com.sopt.withsuhyeon.feature.main.component.MainNavHost
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.colors
import kotlinx.collections.immutable.toPersistentList

@Composable
fun MainScreen(
    navigator: MainNavigator = rememberMainNavigator()
) {
    val snackBarHostState = remember { SnackbarHostState() }

    MainScreenContent(
        navigator = navigator,
        snackBarHostState = snackBarHostState
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun MainScreenContent(
    modifier: Modifier = Modifier,
    navigator: MainNavigator,
    snackBarHostState: SnackbarHostState,
) {
    val imeIsShown = WindowInsets.isImeVisible

    Scaffold(
        modifier = modifier,
        content = { padding ->
            MainNavHost(
                navigator = navigator,
                padding = PaddingValues(
                    start = padding.calculateStartPadding(layoutDirection = LayoutDirection.Ltr),
                    end = padding.calculateEndPadding(layoutDirection = LayoutDirection.Ltr),
                    bottom = if(imeIsShown) 0.dp else padding.calculateBottomPadding(),
                    top = 16.dp
                )

            )
        },
        bottomBar = {
            MainBottomBar(
                modifier = Modifier.background(colors.White)
                    .navigationBarsPadding(),
                visible = navigator.shouldShowBottomBar(),
                tabs = MainTab.entries.toPersistentList(),
                currentTab = navigator.currentTab,
                onTabSelected = { navigator.navigate(it) }
            )
        },
        snackbarHost = { SnackbarHost(snackBarHostState) }
    )
}