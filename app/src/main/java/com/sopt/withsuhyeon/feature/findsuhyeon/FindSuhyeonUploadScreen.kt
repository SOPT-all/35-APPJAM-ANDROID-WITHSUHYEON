package com.sopt.withsuhyeon.feature.findsuhyeon

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.withsuhyeon.core.component.button.LargeButton
import com.sopt.withsuhyeon.core.component.dropdown.text.TextDropDown
import com.sopt.withsuhyeon.feature.findsuhyeon.component.SingleSelectGender
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.colors
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.typography
import com.sopt.withsuhyeon.core.component.dropdown.chip.MediumChipDropDown
import com.sopt.withsuhyeon.core.component.progressbar.AnimatedProgressBar
import com.sopt.withsuhyeon.core.component.textfield.BasicShortTextFieldForPrice
import com.sopt.withsuhyeon.core.type.MediumChipType
import com.sopt.withsuhyeon.core.util.modifier.addFocusCleaner
import com.sopt.withsuhyeon.core.util.modifier.noRippleClickable

@Composable
fun FindSuhyeonUploadRoute(
    padding: PaddingValues,
) {
    FindSuhyeonUploadScreen(
        padding = padding
    )
}
@Composable
fun FindSuhyeonUploadScreen(
    // TODO: navigateToUploadDetail: () -> Unit, 추가하기
    modifier: Modifier = Modifier,
    padding: PaddingValues,
    // TODO: viewModel: FindSuhyeonViewMdel = hiltViewModel()
) {
    val step = 7
    val oneStep = 1f/step
    val focusManager = LocalFocusManager.current
    val scrollState = rememberScrollState()

    var isPriceTextFieldFocused by remember { mutableStateOf(false) }

    val selectedGender = remember { mutableStateOf<String?>(null) }
    var selectedAge by remember { mutableStateOf<String?>(null) }
    var selectedRequirementsList by remember { mutableStateOf(listOf<MediumChipType>()) }
    var selectedLocation by remember { mutableStateOf<String?>(null) }
    var selectedDate by remember { mutableStateOf<String?>(null) }
    var selectedPrice by remember { mutableStateOf<Int?>(null) }

    var isSelectedGender by remember { mutableStateOf(false) }
    var isSelectedAge by remember { mutableStateOf(false) }
    var isSelectedRequirements by remember { mutableStateOf(false) }
    var isSelectedLocation by remember { mutableStateOf(false) }
    var isSelectedDate by remember { mutableStateOf(false) }
    var isSelectedPrice by remember { mutableStateOf(false) }

    var isComplete by remember { mutableStateOf(false) }

    var priceTextValue by remember { mutableStateOf("") }

    var isPriceValid by remember { mutableStateOf(false) }
    var priceButtonEnabled by remember { mutableStateOf(true) }


    var progress by remember { mutableFloatStateOf(oneStep) }

    Column(
        modifier =  modifier.fillMaxSize()
            .padding(padding)
    ) {
        AnimatedProgressBar(
            modifier = Modifier.padding(16.dp),
            progress = progress
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(scrollState)
                .addFocusCleaner(focusManager),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            FindSuhyeonSection(
                title = "주고싶은 금액을 입력해줘",
                visible = isSelectedDate,
                isSelected = isSelectedPrice,
                content = { contentModifier ->
                    BasicShortTextFieldForPrice(
                        modifier = contentModifier.fillMaxWidth()
                            .noRippleClickable {
                                focusManager.clearFocus()
                            },
                        value = priceTextValue,
                        onValueChange = { input ->
                            isPriceValid = input.isNotEmpty()
                            priceTextValue = input
                            priceButtonEnabled = isPriceValid
                        },
                        hint = "금액 입력하기",
                        onFocusChange = { isFocused ->
                            isPriceTextFieldFocused = isFocused
                            if (priceTextValue.isNotEmpty() && !isFocused)
                                selectedPrice = priceTextValue.toInt()
                            isSelectedPrice = priceTextValue.isNotEmpty() && !isFocused
                            if (isSelectedPrice) {
                                progress += oneStep
                                isComplete = true
                            }
                        },
                        keyboardActions = KeyboardActions(onDone = {
                            focusManager.moveFocus(FocusDirection.Next)
                        })

                    )
                }
            )
            FindSuhyeonSection(
                title = "언제 만날지 선택해줘",
                visible = isSelectedLocation,
                isSelected = isSelectedDate,
                content = { contentModifier ->
                    TextDropDown(
                        modifier = contentModifier.fillMaxWidth(),
                        hint = "날짜 및 시간 선택하기",
                        value = remember { mutableStateOf(selectedDate.orEmpty()) },
                        isError = false,
                        onClick = { date ->
                            if (selectedDate == null)
                                progress += oneStep
                            selectedDate = date
                            isSelectedDate = selectedDate != null
                        }
                    )
                }
            )
            FindSuhyeonSection(
                title = "수현이 만날 곳을 선택해줘",
                visible = isSelectedRequirements,
                isSelected = isSelectedLocation,
                content = { contentModifier ->
                    TextDropDown(
                        modifier = contentModifier.fillMaxWidth(),
                        hint = "만날 장소 선택하기",
                        value = remember { mutableStateOf(selectedLocation.orEmpty()) },
                        isError = false,
                        onClick = { location ->
                            if (selectedLocation == null)
                                progress += oneStep
                            selectedLocation = location
                            isSelectedLocation = selectedLocation != null
                        }
                    )
                }
            )
            FindSuhyeonSection(
                title = "요청사항을 선택해줘",
                visible = isSelectedAge,
                isSelected = isSelectedRequirements,
                content = { contentModifier ->
                    MediumChipDropDown(
                        modifier = contentModifier.fillMaxWidth(),
                        hint = "요청사항 선택하기 (중복 선택 가능)",
                        isError = false,
                        onClick = { chipTypes ->
                            if (!isSelectedRequirements)
                                progress += oneStep
                            selectedRequirementsList = chipTypes
                            isSelectedRequirements = selectedRequirementsList.isNotEmpty()
                        }
                    )
                }
            )
            FindSuhyeonSection(
                title = "수현이의 나이를 선택해줘",
                visible = isSelectedGender,
                isSelected = isSelectedAge,
                content = { contentModifier ->
                    TextDropDown(
                        modifier = contentModifier.fillMaxWidth(),
                        hint = "눌러서 나이 선택하기",
                        value = remember { mutableStateOf(selectedAge.orEmpty()) },
                        isError = false,
                        onClick = { age ->
                            if (selectedAge == null)
                                progress += oneStep
                            selectedAge = age
                            isSelectedAge = selectedAge != null
                        }
                    )
                }
            )
            FindSuhyeonSection(
                title = "수현이의 성별을 선택해줘",
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
        LargeButton(
            text = "입력 완료",
            isDisabled = isComplete,
        ) {
            // TODO: navigateToUploadDetail()
        }
    }
}

@Composable
fun FindSuhyeonTitle(
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
fun FindSuhyeonSection(
    title: String,
    visible: Boolean,
    isSelected: Boolean,
    content: @Composable (modifier: Modifier) -> Unit,
    contentModifier: Modifier = Modifier,
) {
    Column {
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
                textStyle = if (isSelected)
                    typography.body03_R.merge(colors.Grey400)
                else
                    typography.title02_B,
                modifier = Modifier.padding(
                    if (isSelected)
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
                    if (isSelected)
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
        padding = PaddingValues(16.dp)
        // TODO: navigateToUploadDetail = {}
    )
}