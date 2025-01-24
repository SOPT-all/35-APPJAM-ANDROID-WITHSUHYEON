package com.sopt.withsuhyeon.feature.mypage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sopt.withsuhyeon.R
import com.sopt.withsuhyeon.core.component.topbar.SubTopNavBar
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.colors
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.typography

@Composable
fun MyPageWithDrawRoute(
    padding: PaddingValues,
    navigateUp: () -> Unit,
    viewModel: MyPageViewModel = hiltViewModel()
) {
    MyPageWithDrawScreen(
        padding = padding,
        onCloseButtonClick = navigateUp,
    )
}
@Composable
fun MyPageWithDrawScreen(
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
            text = "탈퇴하기"
        )
        Column (
            modifier = Modifier.fillMaxSize()
                .background(color = colors.Grey50),
            verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Bottom),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "가입은 맘대로였지만,\n탈퇴는 아니랍니다?",
                style = typography.body01_B.merge(colors.Purple900),
                textAlign = TextAlign.Center
            )
            Text(
                text = "탈퇴하고 싶다면\n우리를 찾아봐요^^",
                style = typography.body01_B.merge(colors.Purple900) ,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 71.dp)
            )
            Image(
                painter = painterResource(R.drawable.img_withdraw),
                modifier = Modifier.fillMaxWidth(),
                contentDescription = "서버 군단",
                contentScale = ContentScale.FillWidth
            )
        }
    }
}
@Composable
@Preview
fun PreviewMyPageWithDrawScreen() {
    MyPageWithDrawScreen(
        PaddingValues(0.dp)
    ) {  }
}