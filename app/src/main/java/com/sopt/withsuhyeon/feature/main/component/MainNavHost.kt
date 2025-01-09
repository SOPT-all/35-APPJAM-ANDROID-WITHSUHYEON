package com.sopt.withsuhyeon.feature.main.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import com.sopt.withsuhyeon.feature.chat.navigation.chatNavGraph
import com.sopt.withsuhyeon.feature.findsuhyeon.navigation.findSuhyeonNavGraph
import com.sopt.withsuhyeon.feature.gallery.navigation.galleryNavGraph
import com.sopt.withsuhyeon.feature.home.navigation.homeNavGraph
import com.sopt.withsuhyeon.feature.main.MainNavigator
import com.sopt.withsuhyeon.feature.mypage.navigation.myPageNavGraph
import com.sopt.withsuhyeon.feature.onboarding.navigation.onBoardingNavGraph

@Composable
fun MainNavHost(
    modifier: Modifier = Modifier,
    navigator: MainNavigator,
    padding: PaddingValues
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)  // TODO: Color 정의 되면 변경
    ) {
        NavHost(
            navController = navigator.navController,
            startDestination = navigator.startDestination,
        ) {
            homeNavGraph(
                padding = padding
            )
            findSuhyeonNavGraph(
                padding = padding,
            )

            galleryNavGraph(
                padding = padding,
            )
            chatNavGraph(
                padding = padding,
            )
            myPageNavGraph(
                padding = padding,
            )
            onBoardingNavGraph(
                padding = padding,
                onNavigateToHome = navigator::navigateToHome,
            )
        }
    }
}