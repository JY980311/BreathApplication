package com.example.breathapplication.calendar.viewmodel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.breathapplication.calendar.state.CalendarState
import com.example.breathapplication.calendar.state.Date
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.temporal.ChronoUnit

@RequiresApi(Build.VERSION_CODES.O)
class CalendarViewModel: ViewModel() {

    private val _calendarState = MutableStateFlow<CalendarState?>(null)
    val calendarState: StateFlow<CalendarState?> = _calendarState.asStateFlow()

    /**현재 날짜를 반환하는 읽기 전용 프로퍼티!*/
    val today: LocalDate
        @RequiresApi(Build.VERSION_CODES.O)
        get() {
            return LocalDate.now()
        }

    init {
        getData(lastSelectedDate = today)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getData(startDate: LocalDate = today, lastSelectedDate: LocalDate){
        Log.d("CalendarViewModel", "getData called with startDate: $startDate, lastSelectedDate: $lastSelectedDate")
        // 기존 로직
        val firstDayWeek = startDate.with(DayOfWeek.MONDAY)
        val endDayWeek = firstDayWeek.plusDays(7)
        val visibleDates = getDatesBetween(firstDayWeek, endDayWeek)
        _calendarState.value = getCalendarState(visibleDates, lastSelectedDate)
    }

    /**
     * Sequence(시퀀스) 순차적인 컬렉션으로 요소의 크기를 특정하지 않고, 나중에 결정할 수 있는 특수한 컬렉션!
     * 특정 값을 생성하기 위해 generateSequence() 함수를 사용한다!
     * 쉽게 생각해서 예제를 들자면
     * val nums: Sequence<Int> = generateSequence(1) { it + 1 } 일 경우 시드 인수를 1을 주고 1씩 증가하도록 시퀀스를 정의!
     * println(nums.take(10).toList())를 통해 1부터 10까지의 값을 출력할 수 있다.
     * */
    @RequiresApi(Build.VERSION_CODES.O)
    private fun getDatesBetween(startDate: LocalDate, endDate: LocalDate): List<LocalDate> {
        val numOfDays = ChronoUnit.DAYS.between(startDate, endDate)
        return generateSequence(startDate) { it.plusDays(1)}
            .take(numOfDays.toInt()) // take() 함수를 통해 시퀀스의 요소를 제한을 수행! (해당 수만큼 제한)
            .toList()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getCalendarState(dateList: List<LocalDate>, lastSelectedDate: LocalDate): CalendarState {
        return CalendarState(
            selectedDate = toItemCalendarState(lastSelectedDate, true),
            visibleDates = dateList.map { toItemCalendarState(it, it.isEqual(lastSelectedDate)) }
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun toItemCalendarState(date: LocalDate, isSelected: Boolean) =
        Date(
            date = date,
            isSelected = isSelected,
            isToday = date.isEqual(today)
        )
}