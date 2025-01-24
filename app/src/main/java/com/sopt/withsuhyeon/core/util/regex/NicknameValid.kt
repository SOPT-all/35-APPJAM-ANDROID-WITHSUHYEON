package com.sopt.withsuhyeon.core.util.regex

fun String.containsSpecialCharacters(): Boolean {
    val specialCharactersRegex = "[^a-zA-Z가-힣ㄱ-ㅎㅏ-ㅣ0-9]".toRegex()
    return specialCharactersRegex.containsMatchIn(this)
}