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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sopt.withsuhyeon.R
import com.sopt.withsuhyeon.core.component.bottombar.PostBottomBar
import com.sopt.withsuhyeon.core.component.bottomsheet.DeletePostBottomSheet
import com.sopt.withsuhyeon.core.component.chip.MediumChip
import com.sopt.withsuhyeon.core.component.modal.AlertModal
import com.sopt.withsuhyeon.core.component.profile.PostProfileInfoRow
import com.sopt.withsuhyeon.core.component.topbar.SubTopNavBar
import com.sopt.withsuhyeon.core.type.MediumChipType
import com.sopt.withsuhyeon.feature.findsuhyeon.component.DetailMeetingInformation
import com.sopt.withsuhyeon.feature.findsuhyeon.viewmodel.FindSuhyeonPostViewModel
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.colors
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.typography

@Composable
fun FindSuhyeonPostRoute(
    id: Long,
    navigateToFindSuhyeon: () -> Unit,
    padding: PaddingValues,
    viewModel: FindSuhyeonPostViewModel = hiltViewModel()
) {
    FindSuhyeonPostScreen(
        id = id,
        onDeleteButtonClick = navigateToFindSuhyeon,
        padding = padding,
        viewModel = viewModel
    )
}
@Composable
fun FindSuhyeonPostScreen(
    id: Long,
    onDeleteButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
    padding: PaddingValues,
    viewModel: FindSuhyeonPostViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(id) {
        viewModel.getFindSuhyeonPostDetail(id)
    }

    val borderColor = colors.Grey100

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
                onCloseBtnClicked = { viewModel.toggleDeleteBottomSheet() },
                modifier = Modifier.drawBehind {
                    val borderThickness = 1.dp.toPx()
                    drawLine(
                        color = borderColor,
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
                    .padding(top = 32.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)
                    .verticalScroll(rememberScrollState()),
            ) {
                if(state.postDetailData.isExpired)
                    MediumChip(
                        mediumChipType = MediumChipType.DURATION_FINISHED,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                Text(
                    text = state.postDetailData.title,
                    style = typography.title01_B.merge(color = colors.Grey900),
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                PostProfileInfoRow(
                    profileImage = state.postDetailData.profileImage,
                    userName = state.postDetailData.nickname,
                    date = state.postDetailData.createdAt,
                    modifier = Modifier.drawBehind {
                        val borderThickness = 1.dp.toPx()
                        drawLine(
                            color = borderColor,
                            start = Offset(0f, size.height),
                            end = Offset(size.width, size.height),
                            strokeWidth = borderThickness
                        )
                    }
                )
                Text(
                    text = state.postDetailData.content,
                    style = typography.body03_R.merge(color = colors.Grey900),
                    modifier = Modifier.padding(vertical = 32.dp)
                )
                state.postDetailData.postDetailInfo.let {
                    DetailMeetingInformation(postDetailInfoModel = it)
                }
            }
        }
        if (state.isDeleteBottomSheetVisible) {
            DeletePostBottomSheet(
                isBottomSheetVisible = true,
                onDeleteClick = {
                    viewModel.toggleDeleteBottomSheet()
                    viewModel.toggleDeleteAlertModal(true)
                },
                onCloseClick = {
                    viewModel.toggleDeleteBottomSheet()
                },
                onDismiss = {
                    viewModel.toggleDeleteBottomSheet()
                }
            )
        }

        if (state.isDeleteAlertModalVisible) {
            AlertModal(
                onDeleteClick = {
                    viewModel.deleteFindSuhyeonPost(id)
                    viewModel.toggleDeleteAlertModal(false)
                    onDeleteButtonClick()
                },
                onCancelClick = {
                    viewModel.toggleDeleteAlertModal(false)
                }
            )
        }
        PostBottomBar(
            price = state.postDetailData.price,
            isMyPost = state.postDetailData.owner,
            isDisabled = state.postDetailData.isExpired,
            onClick = { }
        )
    }
}

@Preview
@Composable
fun PreviewFindSuhyeonPostScreen() {
    FindSuhyeonPostScreen(
        id = 0,
        padding = PaddingValues(0.dp),
        onDeleteButtonClick = { },
    )
}