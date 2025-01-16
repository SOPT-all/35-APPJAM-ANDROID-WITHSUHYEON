package com.sopt.withsuhyeon.feature.findsuhyeon.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.withsuhyeon.core.util.price.toDecimalFormat
import com.sopt.withsuhyeon.R
import com.sopt.withsuhyeon.core.component.chip.MediumChip
import com.sopt.withsuhyeon.core.type.MediumChipType
import com.sopt.withsuhyeon.core.util.KeyStorage.AGE_20_TO_24
import com.sopt.withsuhyeon.core.util.KeyStorage.SHORT_FEMALE
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.colors
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.typography

@Composable
fun DetailMeetingInformation (
    location: String,
    gender: String,
    age: String,
    date: String,
    price: Int,
    mediumChipTypeList: List<MediumChipType>,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
            .fillMaxWidth()
            .background(
                colors.Grey25,
                RoundedCornerShape(24.dp)
            )
            .border(
                1.dp,
                colors.Grey100,
                RoundedCornerShape(24.dp)
            )
            .padding(horizontal = 20.dp, vertical = 16.dp)
    ) {
        Text(
            text = stringResource(R.string.find_suhyeon_detail_meeting_information),
            style = typography.body03_B.merge(color = colors.Grey900)
        )
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
                    text = age,
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
                    MediumChip(
                        mediumChipType = chipType
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
                text = stringResource(R.string.find_suhyeon_detail_meeting_price),
                style = typography.body03_R.merge(color = colors.Grey500)
            )
            Text(
                text = price.toDecimalFormat(),
                style = typography.body03_B.merge(color = colors.Grey700)
            )
        }
    }
}

@Preview
@Composable
fun PreviewDetailMeetingInformation() {
    DetailMeetingInformation(
        location = "강남/역삼/삼성",
        gender = SHORT_FEMALE,
        age = AGE_20_TO_24,
        date = "1월 25일 (토) 오후 2:00",
        price = 5000,
        mediumChipTypeList = listOf(
            MediumChipType.CATEGORY_PHOTO,
            MediumChipType.CATEGORY_VIDEO_CALL,
            MediumChipType.CATEGORY_PHONE_CALL,
        )
    )
}