package com.example.breathapplication.calendar.ui

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.breathapplication.calendar.state.Date
import com.example.breathapplication.calendar.viewmodel.CalendarViewModel
import com.example.breathapplication.ui.theme.Greyscale5
import com.example.breathapplication.ui.theme.Greyscale6
import com.example.breathapplication.ui.theme.Primary1
import com.example.breathapplication.ui.theme.Primary2
import com.example.breathapplication.ui.theme.Typography2
import com.example.breathapplication.viewmodel.DiaryScreenViewModel
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Content(
    viewModel: CalendarViewModel,
    diaryScreenViewModel: DiaryScreenViewModel
) {
    val date by viewModel.calendarState.collectAsStateWithLifecycle()
    val today = LocalDate.now()

    /** 현재 날짜로 초기화된 selectedDate, date?.visibleDates에서 today를 찾고 있음 */
    var selectedDate by remember { mutableStateOf(date?.visibleDates?.find { it.date == today }) }

    Log.d("Content", "Current state: $date")

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        if (date != null) {
            items(items = date!!.visibleDates) { dateItem ->

                ContentItem(
                    dateItem,
                    dateItem == selectedDate
                ) {
                    selectedDate = dateItem

                    val selectedDateString = selectedDate?.toFormattedString() ?: "None"
                    diaryScreenViewModel.setSelectedDate(selectedDateString)
                    if(diaryScreenViewModel.isComplete.value){
                        diaryScreenViewModel.getPostById()
                    }

                    Log.d("Content", "Selected date: $selectedDate")
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ContentItem(
    date: Date,
    isClicked: Boolean,
    onClick: () -> Unit
) {

    val textColor = if (isClicked) Primary1 else Greyscale6
    val textColor2 = if (isClicked) Primary2 else Greyscale5

    Column(
        modifier = Modifier
            .width(21.dp)
            .clickable {
                       onClick()
            },
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = getDayOfWeek(date),
            style = Typography2.subTitle,
            color = textColor
        )
        Text(
            text = date.date.dayOfMonth.toString(),
            style = Typography2.subTitle,
            color = textColor2
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
private fun getDayOfWeek(date: Date): String {
    return when (date.day) {
        "Mon" -> "월"
        "Tue" -> "화"
        "Wed" -> "수"
        "Thu" -> "목"
        "Fri" -> "금"
        "Sat" -> "토"
        "Sun" -> "일"
        else -> "월"
    }
}