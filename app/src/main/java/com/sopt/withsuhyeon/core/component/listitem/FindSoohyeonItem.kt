package com.sopt.withsuhyeon.core.component.listitem

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.withsuhyeon.R
import com.sopt.withsuhyeon.core.component.chip.DayChip
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.colors
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.typography

@Composable
fun FindSuhyeonItem(
    title: String,
    loacation: String,
    date: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 24.dp, horizontal = 16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = title,
                style = typography.body02_B
            )
            DayChip(
                duration = 10
            )
        }
        Spacer(
            modifier = Modifier.height(8.dp)
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.Start)
        ) {
            Text(
                text = loacation,
                style = typography.caption01_R,
                color = colors.Grey500
            )
            Text(
                text = stringResource(R.string.dot),
                style = typography.caption01_R,
                color = colors.Grey500
            )
            Text(
                text = date,
                style = typography.caption01_R,
                color = colors.Grey500
            )
        }
    }
    Spacer(modifier = Modifier
        .fillMaxWidth()
        .height(1.dp)
        .background(color = colors.Grey100))
}

@Preview(showBackground = true)
@Composable
fun PreviewFindSuhyeonItem(
    modifier: Modifier = Modifier
) {
    WithSuhyeonTheme {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            FindSuhyeonItem(
                title = "강남역 수현이 찾아요",
                loacation = "강남/역삼/삼성",
                date = "1월 25일"
            )
            FindSuhyeonItem(
                title = "강남역 수현이 찾아요",
                loacation = "강남/역삼/삼성",
                date = "1월 25일"
            )
            FindSuhyeonItem(
                title = "강남역 수현이 찾아요",
                loacation = "강남/역삼/삼성",
                date = "1월 25일"
            )
        }
    }
}