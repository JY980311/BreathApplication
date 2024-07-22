package com.example.breathapplication.calendar.ui

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.breathapplication.calendar.state.Date
import com.example.breathapplication.calendar.viewmodel.CalendarViewModel
import com.example.breathapplication.ui.theme.Greyscale10
import com.example.breathapplication.ui.theme.Greyscale5
import com.example.breathapplication.ui.theme.Greyscale6
import com.example.breathapplication.ui.theme.Typography2

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Content(
    viewModel: CalendarViewModel
) {
    val date by viewModel.calendarState.collectAsStateWithLifecycle()

    Log.d("Content", "Current state: $date")

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        if (date != null) {
            items(items = date!!.visibleDates) {
                ContentItem(it)
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ContentItem(
    date: Date
) {
    Column(
        modifier = Modifier.width(21.dp),
        verticalArrangement = Arrangement.spacedBy(10.79.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = getDayOfWeek(date),
            style = Typography2.subTitle,
            color = Greyscale6
        )
        Text(
            text = date.date.dayOfMonth.toString(),
            style = Typography2.subTitle,
            color = Greyscale5
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CalendarScreen() {
    val calendarViewModel = remember { CalendarViewModel() }
    var calendarState by remember { mutableStateOf(calendarViewModel.getData(lastSelectedDate = calendarViewModel.today)) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(132.dp)
            .background(Greyscale10)
            .padding(20.dp)
    ) {
        Column {
            Header(
                calendarViewModel,
                {
                    val finalStartDate = it.minusDays(1)
                    calendarState =
                        calendarViewModel.calendarState.value?.selectedDate?.let { it1 ->
                            calendarViewModel.getData(
                                startDate = finalStartDate,
                                lastSelectedDate = it1.date
                            )
                        }!!
                },
                {
                    val finalStartDate = it.plusDays(2)
                    calendarState =
                        calendarViewModel.calendarState.value?.selectedDate?.let { it1 ->
                            calendarViewModel.getData(
                                startDate = finalStartDate,
                                lastSelectedDate = it1.date
                            )
                        }!!
                }
            )
            Spacer(modifier = Modifier.height(12.dp))
            Content(viewModel = calendarViewModel)
        }
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

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun ContentPreview() {
    CalendarScreen()
}