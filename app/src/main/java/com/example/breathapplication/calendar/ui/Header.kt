package com.example.breathapplication.calendar.ui

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.breathapplication.R
import com.example.breathapplication.calendar.viewmodel.CalendarViewModel
import com.example.breathapplication.ui.theme.Greyscale5
import com.example.breathapplication.ui.theme.Greyscale6
import com.example.breathapplication.ui.theme.Typography2
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Header(
    viewModel: CalendarViewModel,
    onPrevClickListener: (LocalDate) -> Unit,
    onNextClickListener: (LocalDate) -> Unit,
) {

    val date by viewModel.calendarState.collectAsStateWithLifecycle()

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            modifier = Modifier.clickable {
               /*val finalStartDate = date?.startDate?.date?.minusDays(7)
                if (finalStartDate != null) {
                    Log.d("Header", "Icon clicked, finalStartDate: $finalStartDate")
                    date?.selectedDate?.let { viewModel.getData(startDate = finalStartDate, lastSelectedDate = it.date) }
                }*/
                onPrevClickListener(date?.startDate!!.date)
            },
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_left_arrow),
            contentDescription = "왼쪽 화살표",
            tint = Greyscale6
        )

        //TODO: 월 별로 변경하게 하기 -> 진행 중
        if (date != null) {
            val startMonth = date!!.startDate.date.monthValue
            Text(
                //TODO: 나중에 변수로 빼서 관리하기 편하게 만들기
                text ="$startMonth" + "월",
                style = Typography2.subTitle,
                color = Greyscale5
            )
        }

        Icon(
            modifier = Modifier.clickable {
                onNextClickListener(date?.endDate!!.date)
            },
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_right_arrow),
            contentDescription = "오른쪽 화살표",
            tint = Greyscale6
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun HeaderPreview() {
    Header(
        viewModel = CalendarViewModel(),
        {},
        {}
    )
}