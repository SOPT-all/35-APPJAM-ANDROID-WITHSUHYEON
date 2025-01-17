package com.sopt.withsuhyeon.core.component.bottomsheet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.sopt.withsuhyeon.R
import com.sopt.withsuhyeon.core.component.button.LargeButton
import com.sopt.withsuhyeon.core.component.chip.CategoryChip
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.colors
import com.sopt.withsuhyeon.ui.theme.WithSuhyeonTheme.typography

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun GalleryCategoryBottomSheet(
    isVisible: Boolean,
    categories: List<String>,
    selectedCategories: List<String>,
    onCategoryChipClick: (String) -> Unit,
    onConfirmClick: () -> Unit,
    onDismiss: () -> Unit
) {
    if (isVisible) {
        ModalBottomSheet(
            onDismissRequest = { onDismiss() },
            sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
            containerColor = colors.White
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Text(
                    text = stringResource(R.string.gallery_category_bottom_sheet_title),
                    style = typography.title02_B,
                    modifier = Modifier.padding(start = 8.dp, bottom = 24.dp)
                )

                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    categories.forEach { category ->
                        CategoryChip(
                            text = category,
                            isSelected = selectedCategories.contains(category),
                            onClick = { onCategoryChipClick(category) }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

                LargeButton(
                    text = stringResource(R.string.gallery_category_bottom_sheet_confirm_btn),
                    isDisabled = selectedCategories.isEmpty(),
                    onClick = { onConfirmClick() }
                )
            }
        }
    }
}