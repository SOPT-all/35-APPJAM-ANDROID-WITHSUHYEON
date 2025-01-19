package com.sopt.withsuhyeon.core.util.time

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.LocalDate
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

internal val YEAR_RANGE = (1905..2006).toList()
internal val MONTH_RANGE = (1..12).toList()

internal val HOUR24_RANGE = (0..23).toList()
internal val HOUR12_RANGE = (1..12).toList()
internal val MINUTE_RANGE = (0..59).toList()

internal val currentDateTime = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())

internal val currentDate = currentDateTime.date
internal val currentYear = currentDateTime.year
internal val currentMonth = currentDateTime.monthNumber
internal val currentMinute = currentDateTime.minute
internal val currentHour = currentDateTime.hour

fun String.toFindSuhyeonUploadDate(): LocalDate {
    val pattern = Regex("(\\d{1,2})월 (\\d{1,2})일.*")
    val match = pattern.matchEntire(this) ?: throw IllegalArgumentException("Invalid date format")
    val (month, day) = match.destructured
    val currentYear = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).year
    return LocalDate(currentYear, month.toInt(), day.toInt())
}

fun String.toAllPostRequestDate(): String {
    val currentYear = Calendar.getInstance().get(Calendar.YEAR)

    val cleanedDate = this.replace(Regex("""\s*\([가-힣]+\)"""), "")

    val inputFormat = SimpleDateFormat("M/dd, yyyy", Locale.KOREA)
    val outputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.KOREA)

    val dateWithYear = "$cleanedDate, $currentYear"
    val date = inputFormat.parse(dateWithYear) ?: throw IllegalArgumentException("Invalid date format")
    return outputFormat.format(date)
}

fun Int.toTime(amPm: String): Int {
    val isPm = amPm == "오후"
    return when {
        this == 12 && isPm -> 12
        this == 12 && !isPm -> 0
        isPm -> this + 12
        else -> this
    }
}

fun LocalDateTime.toFormattedString(): String {
    val dayOfWeekKorean = dayOfWeek.name.toKoreanDay()
    val isAfternoon = hour >= 12
    val amPm = if (isAfternoon) "오후" else "오전"
    val hourIn12HourFormat = if (hour % 12 == 0) 12 else hour % 12
    return "${monthNumber}월 ${dayOfMonth}일 ($dayOfWeekKorean) $amPm $hourIn12HourFormat:${minute.toString().padStart(2, '0')}"
}
fun String.toKoreanDay(): String {
    return when (this) {
        "MONDAY" -> "월"
        "TUESDAY" -> "화"
        "WEDNESDAY" -> "수"
        "THURSDAY" -> "목"
        "FRIDAY" -> "금"
        "SATURDAY" -> "토"
        "SUNDAY" -> "일"
        else -> ""
    }
}