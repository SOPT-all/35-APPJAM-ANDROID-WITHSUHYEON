package com.sopt.withsuhyeon.feature.onboarding

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.sopt.withsuhyeon.R
import com.sopt.withsuhyeon.core.component.topbar.SubTopNavBar

@Composable
fun BlockUserRoute(
    padding: PaddingValues,
    navigateToHome: () -> Unit,
) {
    BlockUserScreen(
        padding = padding,
        navigateToHome = navigateToHome
    )
}

@Composable
fun BlockUserScreen(
    padding: PaddingValues,
    navigateToHome: () -> Unit
) {
    Column(

    ) {
        SubTopNavBar(
            text = "차단 계정 관리",
            btnIcon = painterResource(R.drawable.ic_xclose),
            onCloseBtnClicked = {}
        )
    }
}