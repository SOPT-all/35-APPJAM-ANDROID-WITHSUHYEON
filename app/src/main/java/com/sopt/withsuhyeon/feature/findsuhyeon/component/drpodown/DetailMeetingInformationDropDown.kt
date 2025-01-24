package com.sopt.withsuhyeon.feature.findsuhyeon.component.drpodown

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.withsuhyeon.R
import com.sopt.withsuhyeon.core.component.chip.MediumChip
import com.sopt.withsuhyeon.core.type.MediumChipType
import com.sopt.withsuhyeon.core.util.KeyStorage.AGE_20_TO_24
import com.sopt.withsuhyeon.core.util.KeyStorage.AGE_40
import com.sopt.withsuhyeon.core.util.KeyStorage.FIND_SUHYEON_DETAIL_MEETING_INFORMATION_EXPAND
import com.sopt.withsuhyeon.core.util.KeyStorage.PHONE_CALL
import com.sopt.withsuhyeon.core.util.KeyStorage.SHORT_FEMALE
import com.sopt.withsuhyeon.core.util.KeyStorage.TAKE_A_PHOTO
import com.sopt.withsuhyeon.core.util.KeyStorage.VIDEO_CALL
import com.sopt.withsuhyeon.core.util.modifier.noRippleClickable
import com.sopt.withsuhyeon.core.util.price.toDecimalFormat
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.colors
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.typography

@Composable
fun DetailMeetingInformationDropDown(
    location: String,
    gender: String,
    age: String,
    date: String,
    price: Int,
    mediumChipTypeList: List<String>,
    itemId: String,
    modifier: Modifier = Modifier,
) {
    var expanded by rememberSaveable(itemId) { mutableStateOf(true) }
    Column(
        modifier = modifier
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioNoBouncy,
                    stiffness = Spring.StiffnessMedium,
                ),
            )
            .border(
                width = 1.dp,
                color = colors.Grey100,
                shape = RoundedCornerShape(24.dp)
            )
            .background(
                colors.Grey25,
                RoundedCornerShape(24.dp)
            )
    ) {
        DetailMeetingInformationExpandButton(
            expanded = expanded,
            onClick = { expanded = !expanded },
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    shape = RoundedCornerShape(24.dp),
                    color = Color.Transparent
                )
        )
        AnimatedVisibility(expanded) {
                DetailMeetingInformationDropDownContent(
                    location = location,
                    gender = gender,
                    age = age,
                    date = date,
                    mediumChipTypeList = mediumChipTypeList,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            shape = RoundedCornerShape(
                                topStart = 0.dp,
                                topEnd = 0.dp,
                                bottomStart = 24.dp,
                                bottomEnd = 24.dp
                            ),
                            color = Color.Transparent
                        ),
                    price = price
                )
            }
    }
}

@Composable
fun DetailMeetingInformationExpandButton(
    expanded: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .noRippleClickable(onClick)
            .padding(horizontal = 20.dp, vertical = 16.dp)
    ) {
        Text(
            text = stringResource(R.string.find_suhyeon_detail_meeting_information),
            style = typography.body03_B.merge(color = colors.Grey900)
        )
    }
}
@Composable
fun DetailMeetingInformationDropDownContent(
    location: String,
    gender: String,
    age: String,
    date: String,
    price: Int,
    mediumChipTypeList: List<String>,
    modifier: Modifier = Modifier
) {
    val ageString = if(age != AGE_40)
        "${age}세"
    else
        age
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp, top = 0.dp, bottom = 20.dp)
    ) {
        HorizontalDivider(
            thickness = 1.dp,
            color = colors.Grey100
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.find_suhyeon_detail_meeting_location),
                style = typography.body03_R.merge(color = colors.Grey500)
            )
            Text(
                text = location,
                style = typography.body03_B.merge(color = colors.Grey700)
            )
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.find_suhyeon_detail_meeting_wanted),
                style = typography.body03_R.merge(color = colors.Grey500)
            )
            Row {
                Text(
                    text = gender,
                    style = typography.body03_B.merge(color = colors.Grey700)
                )
                Text(
                    text = stringResource(R.string.dot),
                    style = typography.body03_B.merge(color = colors.Grey300)
                )
                Text(
                    text = ageString,
                    style = typography.body03_B.merge(color = colors.Grey700)
                )
            }
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.find_suhyeon_detail_meeting_date),
                style = typography.body03_R.merge(color = colors.Grey500)
            )
            Text(
                text = date,
                style = typography.body03_B.merge(color = colors.Grey700)
            )
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.find_suhyeon_detail_meeting_requirements),
                style = typography.body03_R.merge(color = colors.Grey500)
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                mediumChipTypeList.forEach { chipType ->
                    val chipTypeMapping = when(chipType) {
                        TAKE_A_PHOTO -> MediumChipType.CATEGORY_PHOTO
                        PHONE_CALL -> MediumChipType.CATEGORY_PHONE_CALL
                        VIDEO_CALL -> MediumChipType.CATEGORY_VIDEO_CALL
                        else -> MediumChipType.CATEGORY_VIDEO_CALL
                }
                    MediumChip(
                        mediumChipType = chipTypeMapping
                    )
                }
            }
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.find_suhyeon_price),
                style = typography.body03_R.merge(color = colors.Grey500)
            )
            Row {
                Text(
                    text = price.toDecimalFormat(),
                    style = typography.body03_B.merge(color = colors.Grey700),
                    modifier = Modifier.padding(end = 2.dp)
                )
                Text(
                    text = stringResource(R.string.find_suhyeon_text_won),
                    style = typography.body03_B.merge(color = colors.Grey400)
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewDetailMeetingInformationExpandButton() {
    Column(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxSize()
    ) {
        DetailMeetingInformationDropDown(
            location = "강남/역삼/삼성",
            gender = SHORT_FEMALE,
            age = AGE_20_TO_24,
            date = "1월 25일 (토) 오후 2:00",
            mediumChipTypeList = listOf(
                TAKE_A_PHOTO
            ),
            itemId = FIND_SUHYEON_DETAIL_MEETING_INFORMATION_EXPAND,
            price = 5000,
        )
    }
}