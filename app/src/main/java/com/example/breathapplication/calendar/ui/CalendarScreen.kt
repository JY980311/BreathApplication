package com.example.breathapplication.calendar.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.breathapplication.calendar.viewmodel.CalendarViewModel
import com.example.breathapplication.ui.theme.Greyscale10

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
@Preview(showBackground = true)
@Composable
fun ContentPreview() {
    CalendarScreen()
}