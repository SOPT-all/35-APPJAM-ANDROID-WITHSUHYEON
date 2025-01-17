package com.sopt.withsuhyeon.feature.gallery

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sopt.withsuhyeon.R
import com.sopt.withsuhyeon.core.component.bottomsheet.GalleryCategoryBottomSheet
import com.sopt.withsuhyeon.core.component.button.LargeButton
import com.sopt.withsuhyeon.core.component.dropdown.text.TextDropDown
import com.sopt.withsuhyeon.core.component.textfield.BasicShortTextField
import com.sopt.withsuhyeon.core.component.textfield.LongTextField
import com.sopt.withsuhyeon.core.component.topbar.SubTopNavBar
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.colors
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.typography

@Composable
fun GalleryUploadRoute(
    padding: PaddingValues
) {
    GalleryUploadScreen(
        padding = padding
    )
}

@Composable
private fun GalleryUploadScreen(
    padding: PaddingValues,
    modifier: Modifier = Modifier,
    viewModel: GalleryViewModel = hiltViewModel()
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(colors.White)
    ) {
        SubTopNavBar(
            text = "업로드",
            btnIcon = painterResource(R.drawable.ic_close),
            isTextVisible = true,
            isBtnVisible = true,
            onCloseBtnClicked = {},
            modifier = modifier
                .fillMaxWidth()
                .background(colors.White)
                .align(Alignment.TopCenter)
        )

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(top = 56.dp, bottom = 72.dp, start = 8.dp, end = 8.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            var isError by remember { mutableStateOf(false) }
            var errorMessage by remember { mutableStateOf("") }
            var isBottomSheetVisible by remember { mutableStateOf(false) }
            var selectedCategory by remember { mutableStateOf("") }
            val categories = listOf(
                "학교", "카페", "회식", "엠티", "자취방", "도서관",
                "수영장/빠지", "바다/계곡", "스키장", "찜질방", "캠핑/글램핑",
                "파티룸", "절/교회 수련회", "해외여행", "공항", "기타"
            )

            if (isBottomSheetVisible) {
                GalleryCategoryBottomSheet(
                    isVisible = isBottomSheetVisible,
                    categories = categories,
                    selectedCategory = selectedCategory,
                    onCategoryChipClick = { category ->
                        selectedCategory = category
                    },
                    onConfirmClick = {
                        isBottomSheetVisible = false
                    },
                    onDismiss = {
                        isBottomSheetVisible = false
                    }
                )
            }

            GalleryUploadWithPicker({})

            HorizontalDivider(
                thickness = 4.dp,
                color = colors.Grey50
            )

            Spacer(modifier = modifier.height(8.dp))

            Text(
                text = "카테고리",
                style = typography.body03_SB,
                color = colors.Grey600,
                modifier = modifier.padding(horizontal = 16.dp)
            )

            TextDropDown(
                value = selectedCategory,
                hint = "눌러서 카테고리 선택하기",
                isError = isError,
                errorMessage = errorMessage,
                onClick = {
                    isBottomSheetVisible = true
                },
                modifier = modifier.padding(horizontal = 16.dp)
            )

            Text(
                text = "제목",
                style = typography.body03_SB,
                color = colors.Grey600,
                modifier = modifier.padding(horizontal = 16.dp)
            )

            var value by remember { mutableStateOf("") }
            var isValid by remember { mutableStateOf(false) }
            var enabled by remember { mutableStateOf(true) }
            var isFocused by remember { mutableStateOf(false) }

            BasicShortTextField(
                value = value,
                hint = "텍스트를 입력해주세요",
                isValid = isValid,
                enabled = enabled,
                onFocusChange = {
                    isFocused = it
                },
                onValueChange = {
                    value = it
                },
                visibleLength = true,
                maxLength = 30,
                modifier = modifier.padding(horizontal = 8.dp)
            )

            Spacer(modifier = modifier.height(16.dp))

            Text(
                text = "설명 (선택)",
                style = typography.body03_SB,
                color = colors.Grey600,
                modifier = modifier.padding(horizontal = 16.dp)
            )

            LongTextField(
                value = "",
                onValueChange = {},
                modifier = modifier.padding(horizontal = 16.dp)
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(colors.White)
                .padding(16.dp)
                .align(BottomCenter)
        ) {
            LargeButton("완료") { }
        }
    }
}

//@Preview
//@Composable
//private fun GalleryUploadScreenPreview() {
//    WithSuhyeonTheme {
//        GalleryUploadScreen()
//    }
//}