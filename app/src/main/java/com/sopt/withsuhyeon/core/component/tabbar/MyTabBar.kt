package com.sopt.withsuhyeon.core.component.tabbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.withsuhyeon.R
import com.sopt.withsuhyeon.core.util.modifier.noRippleClickable
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.colors
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.typography

@Composable
fun MyPostTabBar(
    modifier: Modifier = Modifier
) {
    var selectedTab: Int by remember { mutableIntStateOf(0) }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        val tabs = listOf(
            stringResource(R.string.my_tab_bar_item_find_suhyeon),
            stringResource(R.string.my_tab_bar_item_gallery)
        )

        tabs.forEachIndexed { index, title ->
            Column(
                modifier = Modifier
                    .weight(1f)
                    .noRippleClickable { selectedTab = index },
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = title,
                        style = typography.body01_B,
                        color = if (index == selectedTab) colors.Grey800 else colors.Grey400,
                    )
                }
                if (index == selectedTab) {
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(2.dp)
                            .background(color = colors.Grey500)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMyTabBar(
    modifier: Modifier = Modifier
) {
    WithSuhyeonTheme {
            Column(
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            MyPostTabBar()
        }
    }
}