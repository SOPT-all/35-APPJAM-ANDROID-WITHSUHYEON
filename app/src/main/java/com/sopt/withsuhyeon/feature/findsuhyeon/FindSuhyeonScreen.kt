package com.sopt.withsuhyeon.feature.findsuhyeon

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.BottomEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sopt.withsuhyeon.core.component.bottomsheet.LocationBottomSheet
import com.sopt.withsuhyeon.core.component.dropdown.text.TextSelectDropDownWithIcon
import com.sopt.withsuhyeon.core.component.floatingbutton.AddFindSuhyeonPostButton
import com.sopt.withsuhyeon.core.component.topbar.MainTopNavBar
import com.sopt.withsuhyeon.core.util.KeyStorage.AGE_20_TO_24
import com.sopt.withsuhyeon.domain.entity.PostItemModel
import com.sopt.withsuhyeon.feature.findsuhyeon.component.DateChipListRow
import com.sopt.withsuhyeon.feature.findsuhyeon.component.FindSuhyeonPostItem
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.colors

@Composable
fun FindSuhyeonRoute(
    padding: PaddingValues,
    viewModel: FindSuhyeonViewModel = hiltViewModel()
) {
    FindSuhyeonScreen(
        padding = padding
    )
}
@Composable
private fun FindSuhyeonScreen(
    padding: PaddingValues
) {
    var selectedLocation by remember { mutableStateOf<Pair<String?, String?>?>(null) }
    var selectedMainLocation by remember { mutableStateOf<String?>(null) }
    var selectedSubLocation by remember { mutableStateOf<String?>(null) }
    var isLocationBottomSheetVisible by remember { mutableStateOf(false) }
    val mainLocationList by remember {
        mutableStateOf(
            listOf(
                "전국",
                "서울",
                "부산",
                "제주",
                "인천",
                "강원",
                "경기",
                "경상"
            )
        )
    }
    val subLocationList by remember {
        mutableStateOf(
            listOf(
                listOf(
                    "강남/역삼/삼성",
                    "신사/청담/압구정",
                    "서초/교대/사당/동작",
                    "잠실/송파/강동",
                    "을지로/명동/중구/동대문",
                    "서울역/이태원/용산",
                    "종로/인사동",
                    "홍대/합정/마포/서대문/은평",
                    "여의도/영등포역/목동/양천",
                    "구로/신도림/금천/관악/신림",
                    "김포공항/염창/강서",
                    "건대입구/성수/왕십리",
                    "성북/강북/노원/도봉/중랑"
                ),
                listOf(
                    "해운대/마린시티",
                    "벡스코/센텀시티",
                    "서초/교대/사당/동작",
                    "잠실/송파/강동",
                    "을지로/명동/중구/동대문",
                    "서울역/이태원/용산",
                    "종로/인사동",
                    "홍대/합정/마포/서대문/은평",
                    "여의도/영등포역/목동/양천",
                    "구로/신도림/금천/관악/신림",
                    "김포공항/염창/강서",
                    "건대입구/성수/왕십리",
                    "성북/강북/노원/도봉/중랑"
                )
            )
        )
    }

    var selectedDate by remember { mutableStateOf("전체") }
    val dateList = listOf(
        "전체",
        "1/25 (토)",
        "1/26 (일)",
        "1/27 (월)",
        "1/28 (화)",
        "1/29 (수)",
        "1/30 (목)",
        "1/31 (금)",
    )
    val postList = listOf(
        PostItemModel(
            postId = 0,
            title = "서울역 수현이 구해요ㅠㅠ",
            price = 5000,
            gender = true,
            age = AGE_20_TO_24,
            date = "1월 25일 (토) 오후 2:30",
            matching = true
        ),
        PostItemModel(
            postId = 0,
            title = "서울역 수현이 구해요ㅠㅠ",
            price = 5000,
            gender = true,
            age = AGE_20_TO_24,
            date = "1월 25일 (토) 오후 2:30",
            matching = true
        ),
        PostItemModel(
            postId = 0,
            title = "서울역 수현이 구해요ㅠㅠ",
            price = 5000,
            gender = true,
            age = AGE_20_TO_24,
            date = "1월 25일 (토) 오후 2:30",
            matching = true
        ),
        PostItemModel(
            postId = 0,
            title = "서울역 수현이 구해요ㅠㅠ",
            price = 5000,
            gender = true,
            age = AGE_20_TO_24,
            date = "1월 25일 (토) 오후 2:30",
            matching = true
        ),
        PostItemModel(
            postId = 0,
            title = "서울역 수현이 구해요ㅠㅠ",
            price = 5000,
            gender = true,
            age = AGE_20_TO_24,
            date = "1월 25일 (토) 오후 2:30",
            matching = true
        ),
        PostItemModel(
            postId = 0,
            title = "서울역 수현이 구해요ㅠㅠ",
            price = 5000,
            gender = true,
            age = AGE_20_TO_24,
            date = "1월 25일 (토) 오후 2:30",
            matching = true
        ),
        PostItemModel(
            postId = 0,
            title = "서울역 수현이 구해요ㅠㅠ",
            price = 5000,
            gender = true,
            age = AGE_20_TO_24,
            date = "1월 25일 (토) 오후 2:30",
            matching = true
        ),
        PostItemModel(
            postId = 0,
            title = "서울역 수현이 구해요ㅠㅠ",
            price = 5000,
            gender = true,
            age = AGE_20_TO_24,
            date = "1월 25일 (토) 오후 2:30",
            matching = true
        ),
        PostItemModel(
            postId = 0,
            title = "서울역 수현이 구해요ㅠㅠ",
            price = 5000,
            gender = true,
            age = AGE_20_TO_24,
            date = "1월 25일 (토) 오후 2:30",
            matching = true
        )
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            MainTopNavBar(
                text = "수현이 찾기",
            )
            HorizontalDivider(
                thickness = 1.dp,
                color = colors.Grey100
            )
            TextSelectDropDownWithIcon(
                hint = "",
                isError = false,
                value = selectedSubLocation,
                modifier = Modifier
                    .background(colors.White)
                    .padding(16.dp)
            ) {
                isLocationBottomSheetVisible = !isLocationBottomSheetVisible
            }
            DateChipListRow(
                dateList = dateList,
                selectedDate = selectedDate,
                onSelect = { date ->
                    selectedDate = date
                }
            )
            LazyColumn(
                modifier = Modifier.wrapContentHeight()
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(postList) { post ->
                    FindSuhyeonPostItem(
                        postItemModel = post,
                    )
                }
            }
        }

        if (isLocationBottomSheetVisible) {
            LocationBottomSheet(
                isVisible = true,
                subLocationList = subLocationList,
                onConfirmClick = { location ->
                    isLocationBottomSheetVisible = false
                    selectedLocation = location
                    selectedMainLocation = location.first
                    selectedSubLocation = location.second
                },
                onDismiss = {
                    isLocationBottomSheetVisible = false
                },
                selectedMainLocation = selectedMainLocation,
                mainLocationList = mainLocationList,
                selectedSubLocation = selectedSubLocation,
            )
        }

        AddFindSuhyeonPostButton(
            modifier = Modifier
                .align(BottomEnd)
                .padding(bottom = 16.dp, end = 16.dp),
            onClick = { }
        )
    }
}

@Preview
@Composable
fun PreviewFindSuhyeonScreen() {
    FindSuhyeonScreen(
        padding = PaddingValues(0.dp)
    )
}