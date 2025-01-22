package com.sopt.withsuhyeon.core.component.bottomsheet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.withsuhyeon.R
import com.sopt.withsuhyeon.core.component.bottomsheet.draghandle.BottomSheetDragHandle
import com.sopt.withsuhyeon.core.component.button.LargeButton
import com.sopt.withsuhyeon.core.component.menu.LocationMenu
import com.sopt.withsuhyeon.core.util.modifier.noRippleClickable
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.colors
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationBottomSheet(
    isVisible: Boolean,
    mainLocationList: List<String>,
    subLocationList: List<List<String>>,
    selectedMainLocation: String?,
    selectedSubLocation: String?,
    onConfirmClick: (Pair<String?,String?>) -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    var tempSelectedMainLocation by remember { mutableStateOf(selectedMainLocation) }
    var tempSelectedSubLocation by remember { mutableStateOf(selectedSubLocation) }

    if (isVisible) {
        Box(modifier = modifier.wrapContentHeight()) {
            ModalBottomSheet(
                onDismissRequest = { onDismiss() },
                sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
                containerColor = colors.White,
                modifier = Modifier
                    .fillMaxWidth(),
                dragHandle = {
                    BottomSheetDragHandle()
                }
            ) {
                Column(
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {
                    Text(
                        text = stringResource(R.string.bottom_sheet_title_location),
                        style = typography.title02_B,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 19.dp)
                    ) {
                        LocationMenu(
                            mainLocationList = mainLocationList,
                            subLocationList = subLocationList,
                            selectedMainLocation = tempSelectedMainLocation,
                            selectedSubLocation = tempSelectedSubLocation,
                            modifier = Modifier.height(350.dp),
                            onMainLocationSelect = { selectedMainLocation ->
                                tempSelectedMainLocation = selectedMainLocation
                            },
                            onSubLocationSelect = { selectedSubLocation ->
                                tempSelectedSubLocation = selectedSubLocation
                            }
                        )
                    }
                    LargeButton(
                        text = stringResource(R.string.bottom_sheet_btn_select_done),
                        isDisabled = tempSelectedSubLocation.isNullOrEmpty(),
                        onClick = {
                            onConfirmClick(Pair(tempSelectedMainLocation, tempSelectedSubLocation))
                            onDismiss()
                        }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewLocationBottomSheet() {
    var isBottomSheetVisible by remember { mutableStateOf(true) }
    var selectedMainLocation by remember { mutableStateOf<String?>(null) }
    var selectedSubLocation by remember { mutableStateOf<String?>(null) }
    var selectedLocation by remember { mutableStateOf<Pair<String?,String?>?>(null) }
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
        modifier = Modifier
            .fillMaxSize()
            .background(colors.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Box(
            modifier = Modifier
                .padding(top = 16.dp)
                .noRippleClickable { isBottomSheetVisible = true }
                .padding(horizontal = 24.dp, vertical = 12.dp)
                .background(colors.Purple100),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "지역 바텀 시트"
            )
        }
        if (isBottomSheetVisible) {
            LocationBottomSheet(
                isVisible = true,
                subLocationList = subLocationList,
                onConfirmClick = { location ->
                    selectedLocation = location
                    selectedMainLocation = location.first
                    selectedSubLocation = location.second
                    isBottomSheetVisible = false
                },
                onDismiss = {
                    isBottomSheetVisible = false
                },
                selectedMainLocation = selectedMainLocation,
                mainLocationList = mainLocationList,
                selectedSubLocation = selectedSubLocation,
            )
        }
    }
}