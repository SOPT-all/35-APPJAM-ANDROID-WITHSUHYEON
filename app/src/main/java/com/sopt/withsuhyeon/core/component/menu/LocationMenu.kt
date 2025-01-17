package com.sopt.withsuhyeon.core.component.menu

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.withsuhyeon.core.component.chip.MainLocationChip
import com.sopt.withsuhyeon.core.component.chip.SubLocationChip
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.colors

@Composable
fun LocationMenu(
    mainLocationList: List<String> = emptyList(),
    subLocationList: List<List<String>> =  emptyList(),
    modifier: Modifier = Modifier
) {
    var selectedMainLocationIndex by remember { mutableIntStateOf(0) }
    var selectedMainLocation by remember { mutableStateOf("전국") }
    var selectedSubLocation by remember { mutableStateOf("") }
    Row(
    ) {
        LazyColumn(
            modifier = modifier.weight(0.33f)
        ) {
            items(mainLocationList.size) { index ->
                MainLocationChip(
                    locationName = mainLocationList[index],
                    modifier = Modifier.fillMaxWidth(),
                    isSelected = selectedMainLocation == mainLocationList[index],
                    onClick = {
                        selectedMainLocation = mainLocationList[index]
                        selectedMainLocationIndex = index
                    }
                )
            }
        }
        VerticalDivider(
            thickness = 1.dp,
            color = colors.Grey100
        )
        LazyColumn(
            modifier = modifier.weight(1f)
        ) {
            items(subLocationList[selectedMainLocationIndex].size) { index ->
                if (selectedMainLocation.isNotEmpty()) {
                    SubLocationChip(
                        locationName = subLocationList[selectedMainLocationIndex][index],
                        modifier = Modifier.fillMaxWidth(),
                        isSelected = selectedSubLocation == subLocationList[selectedMainLocationIndex][index],
                        onClick = {
                            selectedSubLocation = subLocationList[selectedMainLocationIndex][index]
                        }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewLocationMenu() {
    val mainLocationList = remember {
        mutableListOf(
            "전국",
            "서울",
            "부산",
            "제주",
            "인천",
            "강원",
            "경기",
            "경상"
        )
    }
    val subLocationList = remember {
        mutableListOf( listOf(
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
    }
    Column(
        verticalArrangement = Arrangement.spacedBy(23.dp),
        modifier = Modifier.background(colors.White)
            .padding(16.dp)
    ) {
        Text(
            text = "자주 활동하는\n" +
                    "지역을 선택해주세요"
        )
        LocationMenu(
            mainLocationList = mainLocationList,
            subLocationList = subLocationList
        )
    }
}