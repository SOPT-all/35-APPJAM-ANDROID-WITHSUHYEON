package com.sopt.withsuhyeon.feature.findsuhyeon

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.isImeVisible
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sopt.withsuhyeon.R
import com.sopt.withsuhyeon.core.component.bottomsheet.AgeBottomSheet
import com.sopt.withsuhyeon.core.component.bottomsheet.DateTimePickerBottomSheet
import com.sopt.withsuhyeon.core.component.bottomsheet.LocationBottomSheet
import com.sopt.withsuhyeon.core.component.bottomsheet.RequirementsBottomSheet
import com.sopt.withsuhyeon.core.component.button.LargeButton
import com.sopt.withsuhyeon.core.component.dropdown.text.TextDropDown
import com.sopt.withsuhyeon.feature.findsuhyeon.component.SingleSelectGender
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.colors
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.typography
import com.sopt.withsuhyeon.core.component.dropdown.chip.MediumChipDropDown
import com.sopt.withsuhyeon.core.component.progressbar.AnimatedProgressBar
import com.sopt.withsuhyeon.core.component.textfield.BasicShortTextFieldForPrice
import com.sopt.withsuhyeon.core.component.topbar.SubTopNavBar
import com.sopt.withsuhyeon.core.util.KeyStorage.AGE_20_TO_24
import com.sopt.withsuhyeon.core.util.KeyStorage.AGE_25_TO_29
import com.sopt.withsuhyeon.core.util.KeyStorage.AGE_30_TO_34
import com.sopt.withsuhyeon.core.util.KeyStorage.AGE_35_TO_39
import com.sopt.withsuhyeon.core.util.KeyStorage.AGE_40
import com.sopt.withsuhyeon.core.util.KeyStorage.PHONE_CALL
import com.sopt.withsuhyeon.core.util.KeyStorage.TAKE_A_PHOTO
import com.sopt.withsuhyeon.core.util.KeyStorage.VIDEO_CALL
import com.sopt.withsuhyeon.core.util.modifier.addFocusCleaner
import com.sopt.withsuhyeon.core.util.modifier.noRippleClickable
import com.sopt.withsuhyeon.core.util.time.currentDateTime
import com.sopt.withsuhyeon.feature.findsuhyeon.type.BottomSheetType
import com.sopt.withsuhyeon.feature.findsuhyeon.viewmodel.FindSuhyeonUploadViewModel

@Composable
fun FindSuhyeonUploadRoute(
    padding: PaddingValues,
    navigateUp: () -> Unit,
    navigateToUploadDetail: () -> Unit,
    viewModel: FindSuhyeonUploadViewModel
) {
    FindSuhyeonUploadScreen(
        padding = padding,
        onCloseButtonClick = navigateUp,
        onCompleteButtonClick = navigateToUploadDetail,
        viewModel = viewModel
    )
}
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FindSuhyeonUploadScreen(
    modifier: Modifier = Modifier,
    padding: PaddingValues,
    onCloseButtonClick: () -> Unit,
    onCompleteButtonClick: () -> Unit,
    viewModel: FindSuhyeonUploadViewModel
) {
    val uploadState by viewModel.uploadState.collectAsStateWithLifecycle()

    val step = 7
    val focusManager = LocalFocusManager.current
    val scrollState = rememberScrollState()

    val density = LocalDensity.current
    val navigationBarsPadding = WindowInsets.navigationBars.getBottom(density).dp

    val imeIsShown = WindowInsets.isImeVisible
    val dividerBorderColor = colors.Grey100
    val priceErrorMessageResource = stringResource(R.string.find_suhyeon_upload_title_error_message)

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(colors.White)
            .padding(padding)
            .padding(bottom = 0.dp)
            .addFocusCleaner(focusManager), verticalArrangement = Arrangement.Bottom
    ) {
        Column(
            modifier = Modifier
                .background(colors.White)
                .weight(1f)
        ) {
            SubTopNavBar(
                btnIcon = painterResource(R.drawable.ic_close),
                isTextVisible = false,
                isBtnVisible = true,
                onCloseBtnClicked = onCloseButtonClick,
                modifier = Modifier.drawBehind {
                    val borderThickness = 1.dp.toPx()
                    drawLine(
                        color = dividerBorderColor,
                        start = Offset(0f, size.height),
                        end = Offset(size.width, size.height),
                        strokeWidth = borderThickness
                    )
                },
                text = ""
            )
            AnimatedProgressBar(
                modifier = Modifier.padding(16.dp),
                progress = uploadState.progress
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp)
                    .padding(bottom = 8.dp)
                    .verticalScroll(scrollState),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val borderColor = when {
                    (uploadState.selectedPrice ?: 0) > 99999 -> colors.Red01
                    uploadState.priceTextValue.isEmpty() && uploadState.isPriceTextFieldFocused -> colors.Purple300
                    uploadState.priceTextValue.isEmpty() && !uploadState.isPriceTextFieldFocused -> colors.Grey100
                    (uploadState.selectedPrice
                        ?: 0) <= 99999 && uploadState.isPriceTextFieldFocused -> colors.Purple300

                    else -> colors.Grey100
                }
                FindSuhyeonSection(
                    title = stringResource(R.string.find_suhyeon_upload_title_price),
                    visible = uploadState.isSelectedDate,
                    isSelected = uploadState.isSelectedPrice,
                    content = { contentModifier ->
                        BasicShortTextFieldForPrice(
                            modifier = contentModifier
                                .fillMaxWidth()
                                .noRippleClickable {
                                    focusManager.clearFocus()
                                },
                            value = uploadState.priceTextValue,
                            onValueChange = { input ->
                                viewModel.updatePrice(input)
                            },
                            hint = stringResource(R.string.find_suhyeon_upload_hint_price),
                            onFocusChange = { isFocused ->
                                viewModel.updateIsPriceTextFieldFocused(isFocused)
                            },
                            textFieldBorderColor = borderColor,
                            keyboardActions = KeyboardActions(onDone = {
                                focusManager.moveFocus(FocusDirection.Next)
                            }),
                            isValid = uploadState.isPriceValid,
                            errorMessage = uploadState.priceErrorMessage
                        )
                    },
                    isLastStep = true
                )
                FindSuhyeonSection(
                    title = stringResource(R.string.find_suhyeon_upload_title_date),
                    visible = uploadState.isSelectedLocation,
                    isSelected = uploadState.isSelectedDate,
                    content = { contentModifier ->
                        TextDropDown(
                            modifier = contentModifier.fillMaxWidth(),
                            hint = stringResource(R.string.find_suhyeon_upload_fint_date),
                            value = uploadState.selectedDateString,
                            isError = false,
                            onClick = {
                                viewModel.toggleBottomSheet(BottomSheetType.DATE)
                            }
                        )
                    }
                )
                FindSuhyeonSection(
                    title = stringResource(R.string.find_suhyeon_upload_title_location),
                    visible = uploadState.isSelectedRequirements,
                    isSelected = uploadState.isSelectedLocation,
                    content = { contentModifier ->
                        TextDropDown(
                            modifier = contentModifier.fillMaxWidth(),
                            hint = stringResource(R.string.find_suhyeon_upload_hint_location),
                            value = uploadState.selectedSubLocation,
                            isError = false,
                            onClick = {
                                viewModel.showLocationBottomSheet()
                            }
                        )
                    }
                )
                FindSuhyeonSection(
                    title = stringResource(R.string.find_suhyeon_upload_title_reqirements),
                    visible = uploadState.isSelectedAge,
                    isSelected = uploadState.isSelectedRequirements,
                    content = { contentModifier ->
                        MediumChipDropDown(
                            modifier = contentModifier.fillMaxWidth(),
                            hint = stringResource(R.string.find_suhyeon_upload_hint_requirements),
                            isError = false,
                            onClick = {
                                viewModel.toggleBottomSheet(BottomSheetType.REQUIREMENTS)
                            },
                            selectedList = uploadState.selectedRequirementsList
                        )
                    }
                )
                FindSuhyeonSection(
                    title = stringResource(R.string.find_suhyeon_upload_title_age),
                    visible = uploadState.isSelectedGender,
                    isSelected = uploadState.isSelectedAge,
                    content = { contentModifier ->
                        TextDropDown(
                            modifier = contentModifier.fillMaxWidth(),
                            hint = stringResource(R.string.find_suhyeon_upload_hint_age),
                            value = uploadState.selectedAge,
                            isError = false,
                            onClick = {
                                viewModel.toggleBottomSheet(BottomSheetType.AGE)
                            }
                        )
                    }
                )
                FindSuhyeonSection(
                    title = stringResource(R.string.find_suhyeon_upload_title_gender),
                    visible = true,
                    isSelected = uploadState.isSelectedGender,
                    content = { contentModifier ->
                        SingleSelectGender(
                            modifier = contentModifier.fillMaxWidth(),
                            selectedGender = uploadState.selectedGender.orEmpty(),
                            onSelect = { gender ->
                                if (uploadState.selectedGender.isNullOrEmpty())
                                    viewModel.updateProgress(2, step)
                                viewModel.updateGender(gender.orEmpty())
                            }
                        )
                    }
                )
            }
            if (uploadState.isDateTimePickerBottomSheetVisible) {
                DateTimePickerBottomSheet(
                    isVisible = true,
                    onConfirmClick = { dateString, date ->
                        viewModel.hideBottomSheet(BottomSheetType.DATE)
                        if (!uploadState.isSelectedDate)
                            viewModel.updateProgress(6, step)
                        viewModel.updateDate(date ?: currentDateTime, dateString.orEmpty())
                    },
                    onDismiss = {
                        viewModel.hideBottomSheet(BottomSheetType.DATE)
                    },
                    modifier = Modifier.padding(bottom = navigationBarsPadding),
                    selectedDateString = uploadState.selectedDateString,
                    selectedDate = uploadState.selectedDate
                )
            }
            //BottomSheet
            if (uploadState.isLocationBottomSheetVisible) {
                LocationBottomSheet(
                    isVisible = true,
                    subLocationList = uploadState.subLocationList,
                    onConfirmClick = { location ->
                        viewModel.updateLocation(location)
                    },
                    onDismiss = {
                        viewModel.hideBottomSheet(BottomSheetType.LOCATION)
                    },
                    selectedMainLocation = uploadState.selectedMainLocation,
                    mainLocationList = uploadState.mainLocationList,
                    selectedSubLocation = uploadState.selectedSubLocation,
                    modifier = Modifier.padding(bottom = navigationBarsPadding)
                )
            }
            if (uploadState.isRequirementsBottomSheetVisible) {
                RequirementsBottomSheet(
                    isVisible = true,
                    requirementsList = listOf(
                        TAKE_A_PHOTO,
                        VIDEO_CALL,
                        PHONE_CALL
                    ),

                    onConfirmClick = { requirements ->
                        viewModel.hideBottomSheet(BottomSheetType.REQUIREMENTS)
                        if (!uploadState.isSelectedRequirements)
                            viewModel.updateProgress(4, step)
                        viewModel.updateRequirements(requirements)
                    },
                    onDismiss = {
                        viewModel.hideBottomSheet(BottomSheetType.REQUIREMENTS)
                    },
                    selectedRequirementsList = uploadState.selectedRequirementsList,
                    modifier = Modifier.padding(bottom = navigationBarsPadding)
                )
            }
            if (uploadState.isAgeBottomSheetVisible) {
                AgeBottomSheet(
                    isVisible = true,
                    ageList = listOf(
                        AGE_20_TO_24,
                        AGE_25_TO_29,
                        AGE_30_TO_34,
                        AGE_35_TO_39,
                        AGE_40
                    ),
                    onConfirmClick = { age ->
                        viewModel.hideBottomSheet(BottomSheetType.AGE)
                        if (!uploadState.isSelectedAge)
                            viewModel.updateProgress(3,step)
                        viewModel.updateAge(age.orEmpty())
                    },
                    onDismiss = {
                        viewModel.hideBottomSheet(BottomSheetType.AGE)
                    },
                    modifier = Modifier.padding(bottom = navigationBarsPadding),
                    selectedAge = uploadState.selectedAge
                )
            }
        }
        if (imeIsShown) {
            LargeButton(
                text = stringResource(R.string.find_suhyeon_upload_text_input_done),
                isDisabled = uploadState.priceTextValue.isEmpty(),
                modifier = Modifier
                    .fillMaxWidth()
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
                    .imePadding(),
                onClick = {
                    if ((uploadState.selectedPrice ?: 0) > 99999)
                        viewModel.updatePrice("${99999}")
                    onCompleteButtonClick()
                },
                isDownloadBtn = false
            )
        }
    }
}

@Composable
private fun FindSuhyeonTitle(
    modifier: Modifier = Modifier,
    text: String = "",
    textStyle: TextStyle = typography.title02_B.merge(colors.Grey950),
) {
    Text(
        text = text,
        style = textStyle,
        modifier = modifier
            .fillMaxWidth()
    )
}

@Composable
 private fun FindSuhyeonSection(
    title: String,
    visible: Boolean,
    isSelected: Boolean,
    content: @Composable (modifier: Modifier) -> Unit,
    contentModifier: Modifier = Modifier,
    isLastStep: Boolean = false
) {
    Column{
        AnimatedVisibility(
            visible = visible,
            enter = slideInVertically(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioLowBouncy,
                    stiffness = Spring.StiffnessLow
                ),
                initialOffsetY = { -it }
            ),
            exit = slideOutVertically(targetOffsetY = { it })
        ) {
            FindSuhyeonTitle(
                text = title,
                textStyle = if (isSelected && !isLastStep)
                    typography.body03_R.merge(colors.Grey400)
                else
                    typography.title02_B.merge(colors.Black),
                modifier = Modifier.padding(
                    if (isSelected && !isLastStep)
                        PaddingValues(top = 24.dp, bottom = 12.dp)
                    else
                        PaddingValues(vertical = 20.dp)
                )
            )
        }
        AnimatedVisibility(
            visible = visible,
            enter = slideInVertically(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioLowBouncy,
                    stiffness = Spring.StiffnessLow
                ),
                initialOffsetY = { -it }
            ),
            exit = slideOutVertically(targetOffsetY = { it })
        ) {
            content(
                contentModifier.padding(
                    if (isSelected && !isLastStep)
                        PaddingValues(top = 8.dp, bottom = 12.dp)
                    else
                        PaddingValues(vertical = 12.dp)
                )
            )
        }
    }
}

@Preview
@Composable
fun PreviewFindSuhyeon2() {
    FindSuhyeonUploadScreen(
        padding = PaddingValues(),
        onCloseButtonClick = { },
        onCompleteButtonClick = { },
        viewModel = hiltViewModel()
    )
}