package com.example.breathapplication.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
class WriteDiaryScreenViewModel: ViewModel() {

    var TopbarCrrentDate by mutableStateOf(formatDate1("2024-07-21"))
    var currentDate by mutableStateOf(formatDate2(TopbarCrrentDate))
    var date by mutableStateOf(formatDate3(TopbarCrrentDate))

    var diaryText by mutableStateOf("")
    var sleepTapClicked = mutableStateListOf(false,false,false)
    private val _selectedTag = mutableStateOf<String?>(null)
    val selectedTag: State<String?> = _selectedTag
    var showDialog by mutableStateOf(false)
    var isContinueWriting by mutableStateOf(false)

    fun selectTag(tag: String?) {
        _selectedTag.value = tag
    }

    // true인 값을 가진 인덱스를 반환하는 함수
    fun getSelectedIndices(): List<Int> {
        return sleepTapClicked.mapIndexedNotNull { index, isSelected ->
            if (isSelected) index else null
        }
    }

    fun getSelectedTag(): List<String> {
        return getSelectedIndices().map { index ->
            when (index) {
                0 -> "과한 운동"
                1 -> "과음"
                2 -> "카페인"
                else -> ""
            }
        }
    }

    fun formatDate1(inputDate: String): String {
        // 입력 형식 정의
        val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        // 날짜 문자열 파싱
        val date = LocalDate.parse(inputDate, inputFormatter)

        // 출력 형식 정의
        val month = date.month.getDisplayName(TextStyle.SHORT, Locale.KOREAN)
        val day = date.dayOfMonth
        val dayOfWeek = date.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.KOREAN)

        return "${month} ${day}일 $dayOfWeek"
    }

    fun formatDate2(originalDateString: String): String {
        val parts = originalDateString.split(" ")
        if (parts.size < 3) return originalDateString

        val dayOfWeek = parts[2]
        val firstLetter = dayOfWeek.first()

        return "${parts[0]} ${parts[1]} $firstLetter"
    }

    fun formatDate3(originalDateString: String): String {
        val parts = originalDateString.split(" ")
        if (parts.size < 3) return originalDateString

        val dayOfWeek = parts[2]

        return "${parts[0]} ${parts[1]}"
    }

}

