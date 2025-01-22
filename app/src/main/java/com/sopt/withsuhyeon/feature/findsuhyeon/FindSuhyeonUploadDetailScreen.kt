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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sopt.withsuhyeon.R
import com.sopt.withsuhyeon.core.component.button.LargeButton
import com.sopt.withsuhyeon.core.component.progressbar.AnimatedProgressBar
import com.sopt.withsuhyeon.core.component.textfield.BasicShortTextField
import com.sopt.withsuhyeon.core.component.textfield.LongTextField
import com.sopt.withsuhyeon.core.component.topbar.SubTopNavBar
import com.sopt.withsuhyeon.core.util.KeyStorage.FEMALE
import com.sopt.withsuhyeon.core.util.KeyStorage.FIND_SUHYEON_DETAIL_MEETING_INFORMATION_EXPAND
import com.sopt.withsuhyeon.core.util.KeyStorage.SHORT_FEMALE
import com.sopt.withsuhyeon.core.util.KeyStorage.SHORT_MALE
import com.sopt.withsuhyeon.core.util.modifier.addFocusCleaner
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
                onCloseBtnClicked = onCloseBtnClick,
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
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    DetailMeetingInformationDropDown(
                        location = uploadState.selectedSubLocation.orEmpty(),
                        gender = if(uploadState.selectedGender.orEmpty() == FEMALE)
                            SHORT_FEMALE
                        else
                            SHORT_MALE,
                        age = uploadState.selectedAge.orEmpty(),
                        date = uploadState.selectedDateString.orEmpty(),
                        mediumChipTypeList = uploadState.selectedRequirementsList,
                        itemId = FIND_SUHYEON_DETAIL_MEETING_INFORMATION_EXPAND,
                        modifier = Modifier.padding(vertical = 20.dp)
                    )
                    BasicShortTextField(
                        title = stringResource(R.string.find_suhyeon_title),
                        value = detailState.titleValue,
                        onValueChange = { newValue ->
                            viewModel.updateTitle(
                                value = newValue,
                                errorMessage = titleLengthErrorMessage)
                        },
                        hint = stringResource(R.string.find_suhyeon_title_hint),
                        isValid = detailState.isTitleValid,
                        errorMessage = detailState.titleErrorMessage,
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
                        value = detailState.findSuhyeonContentValue,
                        hint = stringResource(R.string.find_suhyeon_content_hint),
                        onValueChange = { newDescription ->
                            viewModel.updateContent(
                                newDescription
                            )
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
                    val isTitleInputValid = detailState.titleValue.isNotEmpty()
                    viewModel.updateIsTitleValid(isTitleInputValid)

                    if(!isTitleInputValid)
                        viewModel.updateErrorMessage(titleLengthErrorMessage)
                    if (isTitleInputValid) {
                        onCompleteBtnClick()
                    }
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
        onCompleteBtnClick = { },
        viewModel =  hiltViewModel()
    )
}