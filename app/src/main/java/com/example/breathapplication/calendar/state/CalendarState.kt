package com.example.breathapplication.calendar.state

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class CalendarState(
    val selectedDate:Date,
    val visibleDates: List<Date>
) {
    val startDate: Date get() = visibleDates.first() // the first of the visible dates
    val endDate: Date get() = visibleDates.last() // the last of the visible dates
}

data class Date(
    val date: LocalDate,
    val isSelected: Boolean,
    val isToday: Boolean
) {

    // 날짜를 "yyyy-MM-dd" 형식의 문자열로 변환하는 메서드
    @RequiresApi(Build.VERSION_CODES.O)
    fun toFormattedString(): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        return date.format(formatter)
    }
    val day: String @RequiresApi(Build.VERSION_CODES.O)
    get() = date.format(DateTimeFormatter.ofPattern("E"))  //TODO: 사용할지 모름 일단은 나중에 참고해서 지우기
}