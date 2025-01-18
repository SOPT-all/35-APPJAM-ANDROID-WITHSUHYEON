package com.sopt.withsuhyeon.feature.gallery

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.withsuhyeon.R
import com.sopt.withsuhyeon.core.component.button.LargeButton
import com.sopt.withsuhyeon.core.component.chip.MediumChip
import com.sopt.withsuhyeon.core.component.profile.PostProfileInfoRow
import com.sopt.withsuhyeon.core.component.topbar.SubTopNavBar
import com.sopt.withsuhyeon.core.type.MediumChipType
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.colors
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.typography

@Composable
fun GalleryPostDetailRoute(
    padding: PaddingValues
) {
    GalleryPostDetailScreen(
        padding = padding
    )
}

@Composable
fun GalleryPostDetailScreen(
    padding: PaddingValues,
    modifier: Modifier = Modifier
) {
    val category by remember { mutableStateOf("") }
    val galleryPostTitle by remember { mutableStateOf("") }
    val profileImage by remember { mutableStateOf("") }
    val userName by remember { mutableStateOf("") }
    val date by remember { mutableStateOf("") }
    val galleryPostContent by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .background(colors.White)
    ) {
        SubTopNavBar(
            text = "",
            btnIcon = painterResource(R.drawable.ic_close),
            isTextVisible = true,
            isBtnVisible = true,
            onCloseBtnClicked = {},
            modifier = Modifier
                .fillMaxWidth()
                .background(colors.White)
                .align(Alignment.TopCenter)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 52.dp)
                .verticalScroll(rememberScrollState())
                .background(colors.White)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .background(colors.Grey500)
            )

            Spacer(modifier = Modifier.height(16.dp))

            MediumChip(
                mediumChipType = MediumChipType.CATEGORY,
                dynamicString = category,
                modifier = Modifier.padding(start = 16.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = galleryPostTitle,
                style = typography.body01_SB,
                color = colors.Black,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            PostProfileInfoRow(
                profileImage = profileImage,
                userName = userName,
                date = date,
                views = "00"
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = galleryPostContent,
                style = typography.body03_R,
                color = colors.Black,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(88.dp))
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(colors.White)
                .padding(16.dp)
                .align(Alignment.BottomCenter)
        ) {
            LargeButton(
                text = "다운로드",
                onClick = {},
                isDownloadBtn = true,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview
@Composable
private fun GalleryPostDetailScreenPreview() {
    WithSuhyeonTheme {
//        GalleryPostDetailScreen(
//            "바다", "가평엠티라능", "", "작심이", "1월 1일", "123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890"
//        )
    }
}