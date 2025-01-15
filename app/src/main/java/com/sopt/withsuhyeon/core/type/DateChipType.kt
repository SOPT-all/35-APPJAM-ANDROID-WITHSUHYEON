package com.sopt.withsuhyeon.core.type

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import com.sopt.withsuhyeon.R
import com.sopt.withsuhyeon.ui.theme.defaultWithSuhyeonColors
import com.sopt.withsuhyeon.ui.theme.defaultWithSuhyeonTypography

enum class DateChipType(
    @StringRes val titleResId: Int,
    val selectedTextColor: Color = defaultWithSuhyeonColors.Grey900,
    val unSelectedTextColor: Color = defaultWithSuhyeonColors.Grey400,
    val typography: TextStyle = defaultWithSuhyeonTypography.body03_B,
    val requiresDynamicString: Boolean = false
) {
    ALL(
        titleResId = R.string.type_all,
        requiresDynamicString = false
    ),
    DATE(
        titleResId = R.string.type_date,
        requiresDynamicString = true
    )
}