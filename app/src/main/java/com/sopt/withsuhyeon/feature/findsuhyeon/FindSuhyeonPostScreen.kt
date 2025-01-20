package com.sopt.withsuhyeon.feature.findsuhyeon

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.withsuhyeon.R
import com.sopt.withsuhyeon.core.component.bottombar.PostBottomBar
import com.sopt.withsuhyeon.core.component.bottomsheet.DeletePostBottomSheet
import com.sopt.withsuhyeon.core.component.modal.AlertModal
import com.sopt.withsuhyeon.core.component.profile.PostProfileInfoRow
import com.sopt.withsuhyeon.core.component.topbar.SubTopNavBar
import com.sopt.withsuhyeon.core.type.MediumChipType
import com.sopt.withsuhyeon.core.util.KeyStorage.AGE_20_TO_24
import com.sopt.withsuhyeon.domain.entity.PostDetailInfoModel
import com.sopt.withsuhyeon.feature.findsuhyeon.component.DetailMeetingInformation
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.colors
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.typography

@Composable
fun FindSuhyeonPostScreen(
    modifier: Modifier = Modifier,
    padding: PaddingValues
) {
    var isDeleteBottomSheetVisible by remember { mutableStateOf(false) }
    var isDeleteAlertModalVisible by remember { mutableStateOf (false) }

    val bolderColor = colors.Grey100

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(colors.White)
            .padding(padding)
            .padding(bottom = 0.dp),
        verticalArrangement = Arrangement.Bottom
    ) {
        Column(
            modifier = Modifier
                .background(colors.White)
                .weight(1f)
        ) {
            SubTopNavBar(
                btnIcon = painterResource(R.drawable.ic_menu),
                isTextVisible = false,
                isBtnVisible = true,
                onCloseBtnClicked = { isDeleteBottomSheetVisible = true },
                modifier = Modifier.drawBehind {
                    val borderThickness = 1.dp.toPx()
                    drawLine(
                        color = bolderColor,
                        start = Offset(0f, size.height),
                        end = Offset(size.width, size.height),
                        strokeWidth = borderThickness
                    )
                },
                text = ""
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),
            ) {
                Text(
                    text = "강남역 수현이 구해요", //title 받아오기
                    style = typography.title01_B.merge(color = colors.Grey900),
                    modifier = Modifier.padding(top = 32.dp, bottom = 8.dp)
                )
                PostProfileInfoRow( // TODO: 데이터 받아오기
                    profileImage = "https://via.placeholder.com/150",
                    userName = "작심이",
                    date = "1월 12일",
                    modifier = Modifier.drawBehind {
                        val borderThickness = 1.dp.toPx()
                        drawLine(
                            color = bolderColor,
                            start = Offset(0f, size.height),
                            end = Offset(size.width, size.height),
                            strokeWidth = borderThickness
                        )
                    }
                )
                Text(
                    text = "강남역에서 사진 찍을 수현이 있나요? 강남역에서 사진 찍을 수현이 있나요? 강남역에서 사진 찍을 수현이 있나요?",
                    style = typography.body03_R.merge(color = colors.Grey900),
                    modifier = Modifier.padding(vertical = 32.dp)
                )
                DetailMeetingInformation( // TODO: 데이터 받아오기
                    postDetailInfoModel = PostDetailInfoModel(
                        region = "강남/역삼/삼성",
                        gender = false,
                        age = AGE_20_TO_24,
                        date = "1월 25일 (토) 오후 2:00",
                        price = 5000,
                        requests = listOf(
                            MediumChipType.CATEGORY_PHOTO,
                            MediumChipType.CATEGORY_VIDEO_CALL,
                            MediumChipType.CATEGORY_PHONE_CALL,
                        )
                    )
                )
            }
        }
        if (isDeleteBottomSheetVisible) {
            DeletePostBottomSheet(
                isBottomSheetVisible = isDeleteBottomSheetVisible,
                onDeleteClick = {
                    isDeleteBottomSheetVisible = false
                    isDeleteAlertModalVisible = true
                },
                onCloseClick = {
                    isDeleteBottomSheetVisible = false
                },
                onDismiss = {
                    isDeleteBottomSheetVisible = false
                }
            )
        }

        if (isDeleteAlertModalVisible) {
            AlertModal(
                onDeleteClick = {
                    isDeleteAlertModalVisible = false
                },
                onCancelClick = {
                    isDeleteAlertModalVisible = false
                }
            )
        }
        PostBottomBar(
            price = 5000,
            isMyPost = true,
            isDisabled = false,
            onClick = {  }
        )
    }
}

@Preview
@Composable
fun PreviewFindSuhyeonPostScreen() {
    FindSuhyeonPostScreen(
        padding = PaddingValues(0.dp)
    )
}