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
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.sopt.withsuhyeon.core.util.KeyStorage.MAX_PRICE_STRING
import com.sopt.withsuhyeon.core.util.KeyStorage.PHONE_CALL
import com.sopt.withsuhyeon.core.util.KeyStorage.TAKE_A_PHOTO
import com.sopt.withsuhyeon.core.util.KeyStorage.VIDEO_CALL
import com.sopt.withsuhyeon.core.util.modifier.addFocusCleaner
import com.sopt.withsuhyeon.core.util.modifier.noRippleClickable
import kotlinx.datetime.LocalDateTime

@Composable
fun FindSuhyeonUploadRoute(
    padding: PaddingValues,
) {
    FindSuhyeonUploadScreen(
        padding = padding
    )
}
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FindSuhyeonUploadScreen(
    // TODO: navigateToUploadDetail: () -> Unit, 추가하기
    modifier: Modifier = Modifier,
    padding: PaddingValues,
    // TODO: viewModel: FindSuhyeonViewMdel = hiltViewModel()
) {
    val step = 7
    val oneStep = 1f / step
    val focusManager = LocalFocusManager.current
    val scrollState = rememberScrollState()

    val density = LocalDensity.current
    val navigationBarsPadding = WindowInsets.navigationBars.getBottom(density).dp

    var isPriceTextFieldFocused by remember { mutableStateOf(false) }

    val selectedGender = remember { mutableStateOf<String?>(null) }
    var selectedAge by remember { mutableStateOf<String?>(null) }
    var selectedRequirementsList by remember { mutableStateOf(listOf<String>()) }
    var selectedLocation by remember { mutableStateOf<Pair<String?, String?>?>(null) }
    var selectedMainLocation by remember { mutableStateOf<String?>(null) }
    var selectedSubLocation by remember { mutableStateOf<String?>(null) }
    var selectedDate by remember { mutableStateOf<LocalDateTime?>(null) }
    var selectedDateString by remember { mutableStateOf<String?>(null) }
    var selectedPrice by remember { mutableStateOf<Long?>(null) }

    var isSelectedGender by remember { mutableStateOf(false) }
    var isSelectedAge by remember { mutableStateOf(false) }
    var isSelectedRequirements by remember { mutableStateOf(false) }
    var isSelectedLocation by remember { mutableStateOf(false) }
    var isSelectedDate by remember { mutableStateOf(false) }
    var isSelectedPrice by remember { mutableStateOf(false) }

    var isAgeBottomSheetVisible by remember { mutableStateOf(false) }
    var isRequirementsBottomSheetVisible by remember { mutableStateOf(false) }
    var isLocationBottomSheetVisible by remember { mutableStateOf(false) }
    var isDateTimePickerBottomSheetVisible by remember { mutableStateOf(false) }

    val isComplete =
        isSelectedGender && isSelectedAge && isSelectedRequirements && isSelectedLocation && isSelectedPrice

    var priceTextValue by remember { mutableStateOf("") }
    var isPriceValid by remember { mutableStateOf(false) }
    var priceButtonEnabled by remember { mutableStateOf(true) }
    var priceErrorMessage by remember { mutableStateOf("") }


    var progress by remember { mutableFloatStateOf(oneStep) }

    val imeIsShown = WindowInsets.isImeVisible
    val bolderColor = colors.Grey100
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
                onCloseBtnClicked = { },
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
            AnimatedProgressBar(
                modifier = Modifier.padding(16.dp),
                progress = progress
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp)
                    .verticalScroll(scrollState),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                FindSuhyeonSection(
                    title = stringResource(R.string.find_suhyeon_upload_title_price),
                    visible = isSelectedDate,
                    isSelected = isSelectedPrice,
                    content = { contentModifier ->
                        BasicShortTextFieldForPrice(
                            modifier = contentModifier
                                .fillMaxWidth()
                                .noRippleClickable {
                                    focusManager.clearFocus()
                                },
                            value = priceTextValue,
                            onValueChange = { input ->
                                isPriceValid = input.isNotEmpty()
                                        && (input.length < MAX_PRICE_STRING.length
                                        || (input.length == MAX_PRICE_STRING.length && input <= MAX_PRICE_STRING))
                                priceErrorMessage = if (!isPriceValid)
                                    priceErrorMessageResource
                                else
                                    ""
                                priceTextValue = input
                                if (isPriceValid) {
                                    selectedPrice = priceTextValue.toLong()
                                    isSelectedPrice = true
                                } else {
                                    isSelectedPrice = false
                                }
                                priceButtonEnabled = isPriceValid
                                // TODO: 입력 완료 버튼은 활성화, 자동으로 99,999원으로 변경되도록
                            },
                            hint = stringResource(R.string.find_suhyeon_upload_hint_price),
                            onFocusChange = { isFocused ->
                                isPriceTextFieldFocused = isFocused
                            },
                            keyboardActions = KeyboardActions(onDone = {
                                focusManager.moveFocus(FocusDirection.Next)
                            }),
                            isValid = isPriceValid,
                            errorMessage = priceErrorMessage,
                        )
                    },
                    isLastStep = true
                )
                FindSuhyeonSection(
                    title = stringResource(R.string.find_suhyeon_upload_title_date),
                    visible = isSelectedLocation,
                    isSelected = isSelectedDate,
                    content = { contentModifier ->
                        TextDropDown(
                            modifier = contentModifier.fillMaxWidth(),
                            hint = stringResource(R.string.find_suhyeon_upload_fint_date),
                            value = selectedDateString,
                            isError = false,
                            onClick = {
                                isDateTimePickerBottomSheetVisible =
                                    !isDateTimePickerBottomSheetVisible
                            }
                        )
                    }
                )
                FindSuhyeonSection(
                    title = stringResource(R.string.find_suhyeon_upload_title_location),
                    visible = isSelectedRequirements,
                    isSelected = isSelectedLocation,
                    content = { contentModifier ->
                        TextDropDown(
                            modifier = contentModifier.fillMaxWidth(),
                            hint = stringResource(R.string.find_suhyeon_upload_hint_location),
                            value = selectedSubLocation,
                            isError = false,
                            onClick = {
                                isLocationBottomSheetVisible = !isLocationBottomSheetVisible
                            }
                        )
                    }
                )
                FindSuhyeonSection(
                    title = stringResource(R.string.find_suhyeon_upload_title_reqirements),
                    visible = isSelectedAge,
                    isSelected = isSelectedRequirements,
                    content = { contentModifier ->
                        MediumChipDropDown(
                            modifier = contentModifier.fillMaxWidth(),
                            hint = stringResource(R.string.find_suhyeon_upload_hint_requirements),
                            isError = false,
                            onClick = {
                                isRequirementsBottomSheetVisible = !isRequirementsBottomSheetVisible
                            },
                            selectedList = selectedRequirementsList
                        )
                    }
                )
                FindSuhyeonSection(
                    title = stringResource(R.string.find_suhyeon_upload_title_age),
                    visible = isSelectedGender,
                    isSelected = isSelectedAge,
                    content = { contentModifier ->
                        TextDropDown(
                            modifier = contentModifier.fillMaxWidth(),
                            hint = stringResource(R.string.find_suhyeon_upload_hint_age),
                            value = selectedAge,
                            isError = false,
                            onClick = {
                                isAgeBottomSheetVisible = !isAgeBottomSheetVisible
                            }
                        )
                    }
                )
                FindSuhyeonSection(
                    title = stringResource(R.string.find_suhyeon_upload_title_gender),
                    visible = true,
                    isSelected = isSelectedGender,
                    content = { contentModifier ->
                        SingleSelectGender(
                            modifier = contentModifier.fillMaxWidth(),
                            onSelect = { gender ->
                                if (selectedGender.value == null)
                                    progress += oneStep
                                selectedGender.value = gender
                                isSelectedGender = selectedGender.value != null
                            }
                        )
                    }
                )
            }
            if (isDateTimePickerBottomSheetVisible) {
                DateTimePickerBottomSheet(
                    isVisible = true,
                    onConfirmClick = { dateString, date ->
                        isDateTimePickerBottomSheetVisible = false
                        if (!isSelectedDate)
                            progress += oneStep
                        selectedDateString = dateString
                        selectedDate = date
                        isSelectedDate = !selectedDateString.isNullOrEmpty()
                    },
                    onDismiss = {
                        isRequirementsBottomSheetVisible = false
                    },
                    modifier = Modifier.padding(bottom = navigationBarsPadding),
                    selectedDateString = selectedDateString,
                    selectedDate = selectedDate
                )
            }
            //BottomSheet
            if (isLocationBottomSheetVisible) {
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
                LocationBottomSheet(
                    isVisible = true,
                    subLocationList = subLocationList,
                    onConfirmClick = { location ->
                        isLocationBottomSheetVisible = false
                        if (!isSelectedLocation)
                            progress += oneStep
                        selectedLocation = location
                        selectedMainLocation = location.first
                        selectedSubLocation = location.second
                        isSelectedLocation = !selectedSubLocation.isNullOrEmpty()
                    },
                    onDismiss = {
                        isLocationBottomSheetVisible = false
                    },
                    selectedMainLocation = selectedMainLocation,
                    mainLocationList = mainLocationList,
                    selectedSubLocation = selectedSubLocation,
                    modifier = Modifier.padding(bottom = navigationBarsPadding)
                )
            }
            if (isRequirementsBottomSheetVisible) {
                RequirementsBottomSheet(
                    isVisible = true,
                    requirementsList = listOf(
                        TAKE_A_PHOTO,
                        VIDEO_CALL,
                        PHONE_CALL
                    ),

                    onConfirmClick = { requirements ->
                        isRequirementsBottomSheetVisible = false
                        if (!isSelectedRequirements)
                            progress += oneStep
                        selectedRequirementsList = requirements
                        isSelectedRequirements = selectedRequirementsList.isNotEmpty()
                    },
                    onDismiss = {
                        isRequirementsBottomSheetVisible = false
                    },
                    selectedRequirementsList = selectedRequirementsList,
                    modifier = Modifier.padding(bottom = navigationBarsPadding)
                )
            }
            if (isAgeBottomSheetVisible) {
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
                        isAgeBottomSheetVisible = false
                        if (!isSelectedAge)
                            progress += oneStep
                        selectedAge = age
                        isSelectedAge = !selectedAge.isNullOrEmpty()
                    },
                    onDismiss = {
                        isAgeBottomSheetVisible = false
                    },
                    modifier = Modifier.padding(bottom = navigationBarsPadding),
                    selectedAge = selectedAge
                )
            }
        }
        if (imeIsShown) {
            LargeButton(
                text = stringResource(R.string.find_suhyeon_upload_text_input_done),
                isDisabled = !isComplete,
                modifier = Modifier
                    .fillMaxWidth()
                    .drawBehind {
                        val borderThickness = 1.dp.toPx()
                        drawLine(
                            color = bolderColor,
                            start = Offset(0f, 0f),
                            end = Offset(size.width, 0f),
                            strokeWidth = borderThickness
                        )
                    }
                    .padding(16.dp)
                    .imePadding(),
                onClick = {
                    // TODO: navigateToUploadDetail()
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
                    typography.title02_B,
                modifier = Modifier.padding(
                    if (isSelected && !isLastStep)
                        PaddingValues(top = 24.dp, bottom = 8.dp)
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
                        PaddingValues(bottom = 8.dp)
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
        padding = PaddingValues()
        // TODO: navigateToUploadDetail = {}
    )
}