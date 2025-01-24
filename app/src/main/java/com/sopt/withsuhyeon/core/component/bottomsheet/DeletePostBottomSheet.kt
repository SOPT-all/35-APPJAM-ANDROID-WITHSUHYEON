package com.sopt.withsuhyeon.core.component.bottomsheet

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.withsuhyeon.core.component.bottomsheet.draghandle.BottomSheetDragHandle
import com.sopt.withsuhyeon.core.component.button.DeleteLargeButton
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.colors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeletePostBottomSheet(
    isBottomSheetVisible: Boolean,
    onDeleteClick: () -> Unit,
    onCloseClick: () -> Unit,
    onDismiss: () -> Unit
) {
    if (isBottomSheetVisible) {
        ModalBottomSheet(
            onDismissRequest = { onDismiss() },
            sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
            containerColor = colors.White,
            dragHandle = { BottomSheetDragHandle() }
        ) {
            DeleteLargeButton(
                text = "삭제하기",
                modifier = Modifier.padding(horizontal = 14.dp),
                isDownloadBtn = true,
                onClick = {
                    onDeleteClick()
                }
            )

            Spacer(modifier = Modifier.height(8.dp))

            DeleteLargeButton(
                text = "닫기",
                modifier = Modifier.padding(horizontal = 14.dp),
                isDownloadBtn = false
            ) {
                onCloseClick()
            }
        }
    }
}

@Preview
@Composable
private fun DeletePostBottomSheetPreview() {
    WithSuhyeonTheme {
        DeletePostBottomSheet(true, {}, {}) { }
    }
}