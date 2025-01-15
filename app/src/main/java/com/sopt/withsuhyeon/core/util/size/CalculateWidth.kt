package com.sopt.withsuhyeon.core.util.size

import android.graphics.Typeface
import android.text.TextPaint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun calculateWidth(texts: List<String>, style: TextStyle): Dp {
    val density = LocalDensity.current
    val maxText = texts.maxByOrNull { it.length } ?: ""
    val textWidth = remember(maxText, style) {
        val paint = TextPaint().apply {
            textSize = style.fontSize.value * density.density
            typeface = Typeface.DEFAULT
        }
        paint.measureText(maxText)
    }
    return with(density) { textWidth.toDp() + 16.dp }
}