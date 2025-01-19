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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
        val nickname by remember { mutableStateOf("") }

        MainTopNavBar(
            text = stringResource(R.string.my_page_title)
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
                    contentDescription = stringResource(R.string.my_page_profile_description),
                    modifier = Modifier.size(44.dp)
                )
                Text(
                    text = nickname,
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
                    text = stringResource(R.string.my_page_my_post),
                    style = typography.body02_B,
                    color = colors.Black,
                    modifier = Modifier
                        .padding(vertical = 14.dp)
                )
                Icon(
                    painter = painterResource(R.drawable.ic_right_arrow),
                    contentDescription = stringResource(R.string.my_page_right_arrow_description),
                    tint = colors.Grey400,
                    modifier = Modifier
                        .align(CenterVertically)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = stringResource(R.string.my_page_manage),
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
                        contentDescription = stringResource(R.string.my_page_block_icon_description),
                        tint = colors.Grey600,
                        modifier = Modifier
                            .padding(start = 12.dp, top = 14.dp, bottom = 14.dp)
                    )
                    Text(
                        text = stringResource(R.string.my_page_manage_blocked_account),
                        style = typography.body02_B,
                        modifier = Modifier
                            .padding(vertical = 14.dp)
                    )
                }

                Icon(
                    painter = painterResource(R.drawable.ic_right_arrow),
                    contentDescription = stringResource(R.string.my_page_right_arrow_description),
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
                        contentDescription = stringResource(R.string.my_page_info_icon_description),
                        tint = colors.Grey600,
                        modifier = Modifier
                            .padding(start = 12.dp, top = 14.dp, bottom = 14.dp)
                    )
                    Text(
                        text = stringResource(R.string.my_page_manage_interested_region),
                        style = typography.body02_B,
                        modifier = Modifier
                            .padding(vertical = 14.dp)
                    )
                }

                Icon(
                    painter = painterResource(R.drawable.ic_right_arrow),
                    contentDescription = stringResource(R.string.my_page_right_arrow_description),
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
                    text = stringResource(R.string.my_page_logout),
                    style = typography.body02_B,
                    color = colors.Black,
                    modifier = Modifier
                        .padding(vertical = 14.dp)
                )
                Icon(
                    painter = painterResource(R.drawable.ic_right_arrow),
                    contentDescription = stringResource(R.string.my_page_right_arrow_description),
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
                    text = stringResource(R.string.my_page_withdraw),
                    style = typography.body02_B,
                    color = colors.Red01,
                    modifier = Modifier
                        .padding(vertical = 14.dp)
                )
                Icon(
                    painter = painterResource(R.drawable.ic_right_arrow),
                    contentDescription = stringResource(R.string.my_page_right_arrow_description),
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