package com.sopt.withsuhyeon.core.component.bottomsheet.draghandle

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.colors

@Composable
fun BottomSheetDragHandle() {
    Spacer(
        modifier = Modifier
            .padding(top = 12.dp, bottom = 24.dp)
            .height(4.dp)
            .width(60.dp)
            .background(
                colors.Grey200,
                RoundedCornerShape(11.dp)
            )
    )
}

@Preview
@Composable
fun PreviewBottomSheetDragHandle() {
    BottomSheetDragHandle()
}