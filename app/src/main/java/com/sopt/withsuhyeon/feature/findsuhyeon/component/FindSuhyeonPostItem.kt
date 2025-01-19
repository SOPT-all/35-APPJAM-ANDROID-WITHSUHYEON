package com.sopt.withsuhyeon.feature.findsuhyeon.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.withsuhyeon.core.util.price.toDecimalFormat
import com.sopt.withsuhyeon.R
import com.sopt.withsuhyeon.core.component.chip.MediumChip
import com.sopt.withsuhyeon.core.type.MediumChipType
import com.sopt.withsuhyeon.core.util.KeyStorage.AGE_20_TO_24
import com.sopt.withsuhyeon.core.util.KeyStorage.SHORT_FEMALE
import com.sopt.withsuhyeon.core.util.KeyStorage.SHORT_MALE
import com.sopt.withsuhyeon.domain.entity.PostItemModel
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.colors
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.typography

@Composable
fun FindSuhyeonPostItem(
    postItemModel: PostItemModel,
    mediumChipType: MediumChipType? = null,
    modifier: Modifier = Modifier
) {
    val genderString = if(postItemModel.gender) SHORT_FEMALE else SHORT_MALE
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier
            .fillMaxWidth()
            .background(
                colors.Grey25,
                RoundedCornerShape(24.dp)
            )
            .padding(horizontal = 20.dp, vertical = 16.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = modifier
                .fillMaxWidth()
        ) {
            Text(
                text = postItemModel.title,
                style = typography.body02_SB.merge(color = colors.Grey900)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                if (mediumChipType != null) {
                    MediumChip(
                        mediumChipType = mediumChipType
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(2.dp),
                ) {
                    Text(
                        text = postItemModel.price.toDecimalFormat(),
                        style = typography.body01_B.merge(color = colors.Grey900)
                    )
                    Text(
                        text = stringResource(R.string.find_suhyeon_text_won),
                        style = typography.body01_SB.merge(color = colors.Grey400)
                    )
                }
            }
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = modifier
                .fillMaxWidth()
        ) {
            HorizontalDivider(
                thickness = 1.dp,
                color = colors.Grey100
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.ic_find),
                        contentDescription = stringResource(R.string.find_suhyeon_detail_meeting_wanted),
                        tint = colors.Grey400,
                        modifier = Modifier.size(18.dp)
                    )
                    Text(
                        text = genderString,
                        style = typography.caption01_SB.merge(color = colors.Grey400)
                    )
                    Text(
                        text =  stringResource(R.string.dot),
                        style = typography.caption01_SB.merge(color = colors.Grey400)
                    )
                    Text(
                        text = postItemModel.age,
                        style = typography.caption01_SB.merge(color = colors.Grey400)
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.ic_calander),
                        contentDescription = stringResource(R.string.find_suhyeon_detail_meeting_wanted),
                        tint = colors.Grey400,
                        modifier = Modifier.size(18.dp)
                    )
                    Text(
                        text = postItemModel.date,
                        style = typography.caption01_SB.merge(color = colors.Grey400)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewFindSuhyeonPost() {
    FindSuhyeonPostItem(
        PostItemModel(
            postId = 0,
            title = "서울역 수현이 구해요ㅠㅠ",
            price = 5000,
            gender = true,
            age = AGE_20_TO_24,
            date = "1월 25일 (토) 오후 2:30",
            matching = true
        ),
        mediumChipType = MediumChipType.DURATION_FINISHED
    )
}