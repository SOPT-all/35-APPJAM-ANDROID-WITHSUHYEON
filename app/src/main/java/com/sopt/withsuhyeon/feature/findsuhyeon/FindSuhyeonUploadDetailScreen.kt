package com.sopt.withsuhyeon.feature.findsuhyeon

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sopt.withsuhyeon.R
import com.sopt.withsuhyeon.core.component.button.LargeButton
import com.sopt.withsuhyeon.core.component.modal.AlertModal
import com.sopt.withsuhyeon.core.component.progressbar.AnimatedProgressBar
import com.sopt.withsuhyeon.core.component.textfield.BasicShortTextField
import com.sopt.withsuhyeon.core.component.textfield.LongTextField
import com.sopt.withsuhyeon.core.component.topbar.SubTopNavBar
import com.sopt.withsuhyeon.core.type.MediumChipType
import com.sopt.withsuhyeon.core.util.KeyStorage.AGE_20_TO_24
import com.sopt.withsuhyeon.core.util.KeyStorage.FIND_SUHYEON_DETAIL_MEETING_INFORMATION_EXPAND
import com.sopt.withsuhyeon.core.util.KeyStorage.SHORT_FEMALE
import com.sopt.withsuhyeon.core.util.modifier.addFocusCleaner
import com.sopt.withsuhyeon.feature.findsuhyeon.component.drpodown.DetailMeetingInformationDropDown
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.colors
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.typography

@Composable
fun FindSuhyeonUploadDetailRoute(
    padding: PaddingValues,
    navigateUp: () -> Unit,
    navigateToFindSuhyeon: () -> Unit,
    viewModel: FindSuhyeonViewModel = hiltViewModel()
) {
    FindSuhyeonUploadDetailScreen(
        padding = padding,
        onCloseBtnClick = navigateUp,
        onCompleteBtnClick = navigateToFindSuhyeon
    )
}
@Composable
fun FindSuhyeonUploadDetailScreen(
    modifier: Modifier = Modifier,
    padding: PaddingValues,
    onCloseBtnClick: () -> Unit,
    onCompleteBtnClick: () -> Unit
) {
    val focusManager = LocalFocusManager.current
    var titleValue by remember { mutableStateOf("") }
    var isTitleValid by remember { mutableStateOf(true) }
    val titleLengthErrorMessage = stringResource(R.string.find_suhyeon_detail_title_error_message)
    var titleErrorMessage by remember { mutableStateOf("") }

    var findSuhyeonContentValue by remember { mutableStateOf("") }

    val step = 7
    val oneStep = 1f / step
    var progress by remember { mutableFloatStateOf(oneStep*6) }

    var isDeleteAlertModalVisible by remember { mutableStateOf (false) }

    LaunchedEffect(Unit) {
        progress = oneStep * 6
        progress += oneStep
    }
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(padding)
            .background(colors.White)
            .addFocusCleaner(focusManager)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(colors.White)
        ) {
            SubTopNavBar(
                text = "",
                btnIcon = painterResource(R.drawable.ic_close),
                isTextVisible = true,
                isBtnVisible = true,
                onCloseBtnClicked = {
                    isDeleteAlertModalVisible = !isDeleteAlertModalVisible
                },
                modifier = modifier
                    .fillMaxWidth()
                    .background(colors.White)
            )
            HorizontalDivider(
                thickness = 1.dp,
                color = colors.Grey100
            )
            AnimatedProgressBar(
                modifier = Modifier.padding(16.dp),
                progress = progress
            )
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    DetailMeetingInformationDropDown(
                        location = "강남/역삼/삼성",
                        gender = SHORT_FEMALE,
                        age = AGE_20_TO_24,
                        date = "1월 25일 (토) 오후 2:00",
                        mediumChipTypeList = listOf(
                            MediumChipType.CATEGORY_PHOTO,
                            MediumChipType.CATEGORY_VIDEO_CALL,
                            MediumChipType.CATEGORY_PHONE_CALL,
                        ),
                        itemId = FIND_SUHYEON_DETAIL_MEETING_INFORMATION_EXPAND,
                        modifier = Modifier.padding(vertical = 20.dp)
                    )
                    BasicShortTextField(
                        title = stringResource(R.string.find_suhyeon_title),
                        value = titleValue,
                        onValueChange = { newValue ->
                            titleValue = newValue
                            isTitleValid = newValue.isNotEmpty()
                        },
                        hint = stringResource(R.string.find_suhyeon_title_hint),
                        isValid = isTitleValid,
                        errorMessage = titleErrorMessage,
                        visibleLength = true,
                        maxLength = 30,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 24.dp, bottom = 12.dp)
                    )

                    Text(
                        text = stringResource(R.string.find_suhyeon_content),
                        style = typography.body03_SB,
                        color = colors.Grey600,
                        modifier = modifier.padding(top = 24.dp, bottom = 8.dp)
                    )

                    LongTextField(
                        value = findSuhyeonContentValue,
                        hint = stringResource(R.string.find_suhyeon_content_hint),
                        onValueChange = { newDescription ->
                            findSuhyeonContentValue = newDescription
                        },
                        modifier = modifier.padding(bottom = 12.dp)
                    )
                }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(colors.White)
                .padding(16.dp)
                .align(Alignment.BottomCenter)
        ) {
            LargeButton(
                text = stringResource(R.string.find_suhyeon_detail_done),
                onClick = {
                    val isTitleInputValid = titleValue.isNotEmpty()
                    isTitleValid = isTitleInputValid

                    if(!isTitleInputValid)
                        titleErrorMessage = titleLengthErrorMessage
                    if (isTitleInputValid) {
                        onCompleteBtnClick()
                    }
                }
            )
        }
        if (isDeleteAlertModalVisible) {
            AlertModal(
                onDeleteClick = {
                    isDeleteAlertModalVisible = false
                },
                onCancelClick = {
                    onCloseBtnClick()
                    isDeleteAlertModalVisible = false
                }
            )
        }
    }
}

@Preview
@Composable
fun PreviewFindSuhyeonDetailMeetingInformationScreen() {
    FindSuhyeonUploadDetailScreen(
        padding = PaddingValues(0.dp),
        onCloseBtnClick = {

        },
        onCompleteBtnClick = { }
    )
}