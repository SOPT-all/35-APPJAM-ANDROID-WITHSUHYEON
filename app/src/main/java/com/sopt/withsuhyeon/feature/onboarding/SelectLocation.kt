package com.sopt.withsuhyeon.feature.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sopt.withsuhyeon.R
import com.sopt.withsuhyeon.core.component.button.LargeButton
import com.sopt.withsuhyeon.core.component.chip.MainLocationChip
import com.sopt.withsuhyeon.core.component.menu.LocationMenu
import com.sopt.withsuhyeon.core.component.progressbar.AnimatedProgressBar
import com.sopt.withsuhyeon.core.component.topbar.MainTopNavBar
import com.sopt.withsuhyeon.core.util.KeyStorage.EMPTY_STRING
import com.sopt.withsuhyeon.core.util.KeyStorage.NEXT_BUTTON_TEXT
import com.sopt.withsuhyeon.feature.onboarding.components.OnBoardingTitle
import com.sopt.withsuhyeon.feature.onboarding.viewmodel.SignUpViewModel
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.colors

@Composable
fun SelectLocationRoute(
    padding: PaddingValues,
    navigateToNext: () -> Unit,
    viewModel: SignUpViewModel
) {
    SelectLocationScreen(
        padding = padding,
        onButtonClick = navigateToNext,
        viewModel
    )
}

@Composable
fun SelectLocationScreen(
    padding: PaddingValues,
    onButtonClick: () -> Unit,
    viewModel: SignUpViewModel,
    modifier: Modifier = Modifier,
) {
    val selectedMainLocation by remember { mutableStateOf("") }
    val selectedSubLocation by remember { mutableStateOf("") }
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
        mutableListOf(
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
    }

    val state by viewModel.signUpState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.updateProgress(state.progress + 1f / 8)
    }

    Column(
        modifier = modifier
            .background(color = colors.White)
            .padding(padding)
            .fillMaxSize()
    ) {
        MainTopNavBar(text = EMPTY_STRING)
        HorizontalDivider(
            modifier = Modifier.height(1.dp),
            color = colors.Grey100
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 16.dp)
        ) {
            AnimatedProgressBar(
                progress = state.progress,
                modifier = Modifier.padding(top = 16.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            OnBoardingTitle(text = stringResource(R.string.onboarding_location_select_title))
            LocationMenu(
                mainLocationList = mainLocationList,
                subLocationList = subLocationList,
                selectedMainLocation = selectedMainLocation,
                selectedSubLocation = selectedSubLocation,
                onSubLocationSelect = { region ->
                    viewModel.updateRegion(region)
                }
            )
        }
        HorizontalDivider(modifier = Modifier.height(1.dp))
        Spacer(modifier = Modifier.height(16.dp))
        LargeButton(
            onClick = {
                onButtonClick()
            },
            text = NEXT_BUTTON_TEXT,
            modifier = Modifier.padding(horizontal = 16.dp),
        )
    }
}