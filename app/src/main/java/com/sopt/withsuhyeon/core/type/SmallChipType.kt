package com.sopt.withsuhyeon.core.type

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.sopt.withsuhyeon.R
import com.sopt.withsuhyeon.ui.theme.defaultWithSuhyeonColors
import com.sopt.withsuhyeon.ui.theme.defaultWithSuhyeonTypography

enum class SmallChipType(
    @StringRes val titleResId: Int,
    val backgroundColor: Color,
    val textColor: Color,
    val horizontalPadding: Dp = 6.dp,
    val verticalPadding: Dp = 2.dp,
    val cornerRadius: Dp = 8.dp,
    val typography: TextStyle = defaultWithSuhyeonTypography.caption02_B,
    val requiresDynamicString: Boolean = false
) {
    IMMINENT_D_DAY(
        titleResId = R.string.type_d_day,
        backgroundColor = defaultWithSuhyeonColors.Red03,
        textColor = defaultWithSuhyeonColors.Red01,
        horizontalPadding = 8.dp,
        requiresDynamicString = true
    ),
    D_DAY(
        titleResId = R.string.type_d_day,
        backgroundColor = defaultWithSuhyeonColors.Purple50,
        textColor = defaultWithSuhyeonColors.Purple500,
        horizontalPadding = 8.dp,
        requiresDynamicString = true
    ),
    DURATION_FINISHED(
        titleResId = R.string.type_finished,
        backgroundColor = defaultWithSuhyeonColors.Grey400,
        textColor = defaultWithSuhyeonColors.White,
        requiresDynamicString = false
    ),
    MATCH_FINISHED(
        titleResId = R.string.type_match,
        backgroundColor = defaultWithSuhyeonColors.Grey100,
        textColor = defaultWithSuhyeonColors.Grey500,
        requiresDynamicString = false
    ),
    CATEGORY(
        titleResId = R.string.type_category,
        backgroundColor = defaultWithSuhyeonColors.Purple50,
        textColor = defaultWithSuhyeonColors.Purple400,
        requiresDynamicString = true
    ),
    CHAT_COUNT(
        titleResId = R.string.type_chat,
        backgroundColor = defaultWithSuhyeonColors.Purple50,
        textColor = defaultWithSuhyeonColors.Purple900,
        horizontalPadding = 6.dp,
        requiresDynamicString = true
    )
}