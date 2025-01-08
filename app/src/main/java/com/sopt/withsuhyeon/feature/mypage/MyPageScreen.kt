package com.sopt.withsuhyeon.feature.mypage

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun MyPageRoute(
    padding: PaddingValues,
    viewModel: MyPageViewModel = hiltViewModel()
) {
    MyPageScreen(
        padding = padding
    )
}
@Composable
private fun MyPageScreen(
    padding: PaddingValues
) {

}