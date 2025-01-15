package com.example.market_kurly.core.util.price

import java.text.DecimalFormat

fun Int.toDecimalFormat(): String {
    val decimalFormat = DecimalFormat("#,###")
    return decimalFormat.format(this)
}
