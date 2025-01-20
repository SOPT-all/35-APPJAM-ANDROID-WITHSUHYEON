package com.sopt.withsuhyeon.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sopt.withsuhyeon.core.component.bottomsheet.GalleryCategoryBottomSheet
import com.sopt.withsuhyeon.core.util.modifier.noRippleClickable
import com.sopt.withsuhyeon.ui.theme.Purple100

@Composable
fun HomeRoute(
    padding: PaddingValues,
    viewModel: HomeViewModel = hiltViewModel()
) {
    HomeScreen(
        padding = padding
    )
}
@Composable
private fun HomeScreen(
    padding: PaddingValues
) {
    Column(
        modifier = Modifier.fillMaxSize()
            .padding(padding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Home Screen"
        )

        var isBottomSheetVisible by remember { mutableStateOf(false) }
        var selectedCategories by remember { mutableStateOf(listOf<String>()) }
        val categories = listOf(
            "학교", "카페", "회식", "엠티", "자취방", "도서관",
            "수영장/빠지", "바다/계곡", "스키장", "찜질방", "캠핑/글램핑",
            "파티룸", "절/교회 수련회", "해외여행", "공항", "기타"
        )

        Box(
            modifier = Modifier
                .padding(top = 16.dp)
                .noRippleClickable { isBottomSheetVisible = true }
                .padding(horizontal = 24.dp, vertical = 12.dp)
                .background(Purple100),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "카테고리 바텀 시트"
            )
        }

//        if (isBottomSheetVisible) {
//            GalleryCategoryBottomSheet(
//                isVisible = isBottomSheetVisible,
//                categories = categories,
//                selectedCategories = selectedCategories,
//                onCategoryChipClick = { category ->
//                    selectedCategories = if (selectedCategories.contains(category)) {
//                        selectedCategories - category
//                    } else {
//                        selectedCategories + category
//                    }
//                },
//                onConfirmClick = {
//                    isBottomSheetVisible = false
//                },
//                onDismiss = {
//                    isBottomSheetVisible = false
//                }
//            )
//        }
    }
}