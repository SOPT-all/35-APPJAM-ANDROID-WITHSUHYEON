package com.sopt.withsuhyeon.core.util.regex

fun String.checkValidPhoneNumber(): Boolean {
    val phoneNumberRegex = Regex("^010\\d{8}$")
    return this.matches(phoneNumberRegex)
}