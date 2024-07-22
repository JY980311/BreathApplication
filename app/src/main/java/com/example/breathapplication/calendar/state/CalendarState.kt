package com.example.breathapplication.calendar.state

import java.time.LocalDate
import java.time.LocalDateTime
import java.util.Date

data class CalendarState (
    val selectedDate: Date,
    val visibleDates: List<Date>
)

data class Date(
    val date: LocalDate,
    val isSelected: Boolean,
    val isToday: Boolean
)