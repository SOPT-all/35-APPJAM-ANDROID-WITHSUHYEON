package com.sopt.withsuhyeon.core.component.bottomsheet

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.sopt.withsuhyeon.R
import com.sopt.withsuhyeon.core.util.modifier.noRippleClickable
import com.sopt.withsuhyeon.ui.theme.Grey500
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.colors
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BlockBottomSheet(
    closeSheet: () -> Unit,
    navigateToBlockScreen: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val sheetState = rememberModalBottomSheetState()
    ModalBottomSheet(
        onDismissRequest = {closeSheet()},
        sheetState = sheetState,
        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
        containerColor = colors.Grey500,
        dragHandle = null
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .background(colors.White),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 16.dp, end = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.End),
            ) {
                Icon(
                    modifier = Modifier.noRippleClickable {
                        closeSheet()
                    },
                    imageVector = ImageVector.vectorResource(R.drawable.ic_xclose),
                    contentDescription = stringResource(R.string.x_close_description),
                    tint = colors.Grey400
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top),
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(R.string.block_bottomsheet_title),
                    style = typography.title02_B,
                    color = colors.Black
                )
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(R.string.block_bottomsheet_sub_title),
                    style = typography.body03_SB,
                    color = colors.Grey500
                )
            }
            Image(
                modifier = Modifier.size(216.dp),
                imageVector = ImageVector.vectorResource(R.drawable.img_fuckoff),
                contentDescription = ""
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            colors.Purple500,
                            shape = RoundedCornerShape(size = 16.dp)
                        )
                        .padding(vertical = 14.dp)
                        .noRippleClickable {
                            navigateToBlockScreen()
                            closeSheet()
                        },
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = stringResource(R.string.block_bottomsheet_button_text),
                        color = colors.White,
                        style = typography.body01_B
                    )
                }

                Column(
                    modifier = modifier
                        .padding(8.dp, bottom = 20.dp)
                        .noRippleClickable {}
                        .drawBehind {
                            val lineY = size.height
                            drawLine(
                                color = Grey500,
                                start = Offset(0f, lineY),
                                end = Offset(size.width, lineY),
                                strokeWidth = 1.dp.toPx()
                            )
                        },
                ) {

                    Text(
                        modifier = Modifier
                            .noRippleClickable {
                                closeSheet()
                            },
                        text = stringResource(R.string.block_bottomsheet_skip_text),
                        color = colors.Grey500,
                        style = typography.body03_SB
                    )
                }
            }
        }
    }
}