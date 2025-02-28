package com.sopt.withsuhyeon.feature.findsuhyeon

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sopt.withsuhyeon.R
import com.sopt.withsuhyeon.core.component.button.LargeButton
import com.sopt.withsuhyeon.core.component.modal.AlertModal
import com.sopt.withsuhyeon.core.component.progressbar.AnimatedProgressBar
import com.sopt.withsuhyeon.core.component.textfield.BasicLongTextField
import com.sopt.withsuhyeon.core.component.textfield.BasicShortTextField
import com.sopt.withsuhyeon.core.component.topbar.SubTopNavBar
import com.sopt.withsuhyeon.core.util.KeyStorage.EXIT_ALERT_TYPE
import com.sopt.withsuhyeon.core.util.KeyStorage.FEMALE
import com.sopt.withsuhyeon.core.util.KeyStorage.FIND_SUHYEON_DETAIL_MEETING_INFORMATION_EXPAND
import com.sopt.withsuhyeon.core.util.KeyStorage.LONG_TEXTFIELD_MAX_LENGTH
import com.sopt.withsuhyeon.core.util.KeyStorage.SHORT_FEMALE
import com.sopt.withsuhyeon.core.util.KeyStorage.SHORT_MALE
import com.sopt.withsuhyeon.core.util.KeyStorage.SHORT_TEXTFIELD_MAX_LENGTH
import com.sopt.withsuhyeon.core.util.modifier.addFocusCleaner
import com.sopt.withsuhyeon.core.util.modifier.noRippleClickable
import com.sopt.withsuhyeon.feature.findsuhyeon.component.drpodown.DetailMeetingInformationDropDown
import com.sopt.withsuhyeon.feature.findsuhyeon.viewmodel.FindSuhyeonUploadViewModel
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.colors
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.typography

@Composable
fun FindSuhyeonUploadDetailRoute(
    padding: PaddingValues,
    navigateUp: () -> Unit,
    navigateToFindSuhyeon: () -> Unit,
    viewModel: FindSuhyeonUploadViewModel,
) {
    FindSuhyeonUploadDetailScreen(
        padding = padding,
        onCloseBtnClick = navigateUp,
        onCompleteBtnClick = navigateToFindSuhyeon,
        viewModel = viewModel
    )
}
@Composable
fun FindSuhyeonUploadDetailScreen(
    modifier: Modifier = Modifier,
    padding: PaddingValues,
    onCloseBtnClick: () -> Unit,
    onCompleteBtnClick: () -> Unit,
    viewModel: FindSuhyeonUploadViewModel
) {
    val uploadState by viewModel.uploadState.collectAsStateWithLifecycle()
    val detailState by viewModel.detailState.collectAsStateWithLifecycle()

    val focusManager = LocalFocusManager.current

    val titleLengthErrorMessage = stringResource(R.string.find_suhyeon_detail_title_error_message)
    val contentErrorMessage = stringResource(R.string.find_suhyeon_detail_title_error_message)

    val dividerBorderColor = colors.Grey100

    BackHandler {
        viewModel.toggleAlert()
    }
    LaunchedEffect(Unit) {
        viewModel.updateProgress(7, 7)
    }
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(padding)
            .background(colors.White)
            .addFocusCleaner(focusManager)
    ) {
        if(detailState.isDeleteAlertModalVisible)
            AlertModal(
                onDeleteClick = {
                    viewModel.toggleAlert()
                    onCloseBtnClick()
                },
                onCancelClick = {
                    viewModel.toggleAlert()
                },
                alertModalType = EXIT_ALERT_TYPE
            )
        Column {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(colors.White)
            ) {
                SubTopNavBar(
                    text = "",
                    btnIcon = painterResource(R.drawable.ic_close),
                    isTextVisible = true,
                    isBtnVisible = true,
                    onCloseBtnClicked = {
                        viewModel.toggleAlert()
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
                    progress = uploadState.progress
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .background(colors.White)
                    .verticalScroll(rememberScrollState())
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    DetailMeetingInformationDropDown(
                        location = uploadState.selectedSubLocation.orEmpty(),
                        gender = if (uploadState.selectedGender.orEmpty() == FEMALE)
                            SHORT_FEMALE
                        else
                            SHORT_MALE,
                        age = uploadState.selectedAge.orEmpty(),
                        date = uploadState.selectedDateString.orEmpty(),
                        mediumChipTypeList = uploadState.selectedRequirementsList,
                        itemId = FIND_SUHYEON_DETAIL_MEETING_INFORMATION_EXPAND,
                        modifier = Modifier.padding(vertical = 20.dp),
                        price = uploadState.selectedPrice ?: 0
                    )
                    val titleBorderColor = when {
                        !detailState.isTitleValid -> colors.Red01
                        detailState.titleValue.length > SHORT_TEXTFIELD_MAX_LENGTH -> colors.Red01
                        detailState.isTitleFocused -> colors.Purple300
                        !detailState.isTitleFocused -> colors.Grey100
                        else -> colors.Grey100
                    }
                    BasicShortTextField(
                        title = stringResource(R.string.find_suhyeon_title),
                        value = detailState.titleValue,
                        onValueChange = { newValue ->
                            viewModel.updateTitle(value = newValue)
                        },
                        onFocusChange = { isFocused ->
                            viewModel.updateIsTitleFocused(isFocused)
                        },
                        hint = stringResource(R.string.find_suhyeon_title_hint),
                        isValid = detailState.isTitleValid,
                        errorMessage = detailState.titleErrorMessage,
                        visibleLength = true,
                        maxLength = 30,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 24.dp, bottom = 12.dp)
                            .imePadding()
                            .noRippleClickable {
                                focusManager.clearFocus()
                            },
                        textFieldBorderColor = titleBorderColor
                    )

                    Text(
                        text = stringResource(R.string.find_suhyeon_content),
                        style = typography.body03_SB,
                        color = colors.Grey600,
                        modifier = modifier.padding(top = 24.dp, bottom = 8.dp)
                    )
                    val contentBorderColor = when {
                        !detailState.isContentValid -> colors.Red01
                        detailState.contentValue.length > LONG_TEXTFIELD_MAX_LENGTH -> colors.Red01
                        detailState.isContentFocused -> colors.Purple300
                        !detailState.isContentFocused -> colors.Grey100
                        else -> colors.Grey100
                    }
                    BasicLongTextField(
                        value = detailState.contentValue,
                        hint = stringResource(R.string.find_suhyeon_content_hint),
                        maxLength = LONG_TEXTFIELD_MAX_LENGTH,
                        visibleLength = true,
                        textFieldBorderColor = contentBorderColor,
                        onValueChange = { newDescription ->
                            viewModel.updateContent(
                                newDescription
                            )
                        },
                        onFocusChange = {
                            viewModel.updateIsContentFocused(
                                detailState.isContentFocused
                            )
                        },
                        modifier = Modifier.height(248.dp),
                        errorMessage = detailState.contentErrorMessage
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                }

                Spacer(Modifier.height(400.dp))
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(colors.White)
                .drawBehind {
                    val borderThickness = 1.dp.toPx()
                    drawLine(
                        color = dividerBorderColor,
                        start = Offset(0f, 0f),
                        end = Offset(size.width, 0f),
                        strokeWidth = borderThickness
                    )
                }
                .padding(16.dp)
                .align(Alignment.BottomCenter)

        ) {
            LargeButton(
                text = stringResource(R.string.find_suhyeon_detail_done),
                onClick = {
                    val isTitleInputValid = detailState.titleValue.isNotEmpty()
                    viewModel.updateIsTitleValid(isTitleInputValid)
                    if(!isTitleInputValid)
                        viewModel.updateTitleErrorMessage(titleLengthErrorMessage)

                    val isContentInputValid = detailState.contentValue.isNotEmpty()
                    viewModel.updateIsContentValid(isContentInputValid)
                    if(!isContentInputValid)
                        viewModel.updateContentErrorMessage(contentErrorMessage)

                    if (isTitleInputValid && isContentInputValid) {
                        viewModel.postFindSuhyeonUpload()
                        onCompleteBtnClick()
                    }
                },
                modifier = Modifier.imePadding()
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
        onCompleteBtnClick = { },
        viewModel =  hiltViewModel()
    )
}