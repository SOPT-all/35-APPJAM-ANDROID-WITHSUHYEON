package com.sopt.withsuhyeon.feature.mypage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sopt.withsuhyeon.R
import com.sopt.withsuhyeon.core.component.topbar.MainTopNavBar
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.colors
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.typography

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
    Column(
        modifier = Modifier.fillMaxSize()
            .padding(padding)
            .background(colors.Grey50),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        MainTopNavBar(
            text = "마이페이지"
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(
                    color = colors.White,
                    shape = RoundedCornerShape(16.dp)
                )
        ) {
            Row(
               modifier = Modifier
                   .fillMaxWidth()
                   .padding(horizontal = 20.dp, vertical = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_profile),
                    contentDescription = null,
                    modifier = Modifier.size(44.dp)
                )
                Text(
                    text = "작심이",
                    style = typography.body02_B,
                    modifier = Modifier
                        .align(CenterVertically)
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, bottom = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "내 게시물",
                    style = typography.body02_B,
                    color = colors.Black,
                    modifier = Modifier
                        .padding(vertical = 14.dp)
                )
                Icon(
                    painter = painterResource(R.drawable.ic_right_arrow),
                    contentDescription = null,
                    tint = colors.Grey400,
                    modifier = Modifier
                        .align(CenterVertically)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "관리",
            style = typography.body03_B,
            color = colors.Grey900,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(start = 16.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 8.dp)
                .background(
                    color = colors.White,
                    shape = RoundedCornerShape(16.dp)
                )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_block),
                        contentDescription = null,
                        tint = colors.Grey600,
                        modifier = Modifier
                            .padding(start = 12.dp, top = 14.dp, bottom = 14.dp)
                    )
                    Text(
                        text = "차단계정 관리",
                        style = typography.body02_B,
                        modifier = Modifier
                            .padding(vertical = 14.dp)
                    )
                }

                Icon(
                    painter = painterResource(R.drawable.ic_right_arrow),
                    contentDescription = null,
                    tint = colors.Grey400,
                    modifier = Modifier
                        .align(CenterVertically)
                        .padding(end = 12.dp)
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_info),
                        contentDescription = null,
                        tint = colors.Grey600,
                        modifier = Modifier
                            .padding(start = 12.dp, top = 14.dp, bottom = 14.dp)
                    )
                    Text(
                        text = "관심 지역 설정",
                        style = typography.body02_B,
                        modifier = Modifier
                            .padding(vertical = 14.dp)
                    )
                }

                Icon(
                    painter = painterResource(R.drawable.ic_right_arrow),
                    contentDescription = null,
                    tint = colors.Grey400,
                    modifier = Modifier
                        .align(CenterVertically)
                        .padding(end = 12.dp)
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 32.dp)
                .background(
                    color = colors.White,
                    shape = RoundedCornerShape(16.dp)
                )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, top = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "로그아웃",
                    style = typography.body02_B,
                    color = colors.Black,
                    modifier = Modifier
                        .padding(vertical = 14.dp)
                )
                Icon(
                    painter = painterResource(R.drawable.ic_right_arrow),
                    contentDescription = null,
                    tint = colors.Grey400,
                    modifier = Modifier
                        .align(CenterVertically)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, bottom = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "탈퇴하기",
                    style = typography.body02_B,
                    color = colors.Red01,
                    modifier = Modifier
                        .padding(vertical = 14.dp)
                )
                Icon(
                    painter = painterResource(R.drawable.ic_right_arrow),
                    contentDescription = null,
                    tint = colors.Grey400,
                    modifier = Modifier
                        .align(CenterVertically)
                )
            }
        }
    }
}

@Preview
@Composable
private fun MyPageScreenPreview() {
    WithSuhyeonTheme {
        MyPageScreen(padding = PaddingValues())
    }
}