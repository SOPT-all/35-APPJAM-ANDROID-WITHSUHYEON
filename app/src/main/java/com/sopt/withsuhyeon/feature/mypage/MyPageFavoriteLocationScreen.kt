package com.sopt.withsuhyeon.feature.mypage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sopt.withsuhyeon.R
import com.sopt.withsuhyeon.core.component.topbar.SubTopNavBar
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.colors

@Composable
fun MyPageFavoriteLocationRoute(
    padding: PaddingValues,
    navigateUp: () -> Unit,
    viewModel: MyPageViewModel = hiltViewModel()
) {
    MyPageFavoriteLocationScreen(
        padding = padding,
        onCloseButtonClick = navigateUp,
    )
}
@Composable
fun MyPageFavoriteLocationScreen(
    padding: PaddingValues,
    modifier: Modifier = Modifier,
    onCloseButtonClick: () -> Unit,
) {
    val dividerBorderColor = colors.Grey100
    Column(
        modifier = modifier.padding(padding)
    ){
        SubTopNavBar(
            btnIcon = painterResource(R.drawable.ic_close),
            isTextVisible = true,
            isBtnVisible = true,
            onCloseBtnClicked = onCloseButtonClick,
            modifier = Modifier.drawBehind {
                val borderThickness = 1.dp.toPx()
                drawLine(
                    color = dividerBorderColor,
                    start = Offset(0f, size.height),
                    end = Offset(size.width, size.height),
                    strokeWidth = borderThickness
                )
            },
            text = "관심지역 설정"
        )
        Box(
            modifier = Modifier.fillMaxSize()
                .background(color = colors.Grey50),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.img_wip),
                tint = Color.Unspecified,
                contentDescription = "",
                modifier = Modifier.width(230.dp)
                    .height(277.dp)
            )
        }
    }
}
@Composable
@Preview
fun PreviewMyPageFavoriteLocationScreen() {
    MyPageFavoriteLocationScreen(
        PaddingValues(0.dp)
    ) {  }
}