package com.example.breathapplication.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
class DiaryScreenViewModel : ViewModel() {

    /** Write Screen */
    private val _selectedDate = mutableStateOf("")
    val selectedDate: State<String> get() = _selectedDate

    private val _TopbarDate = mutableStateOf("")
    val TopbarDate: State<String> get() = _TopbarDate

    private val _subTitleDate = mutableStateOf("")
    val subTitleDate: State<String> get() = _subTitleDate

    private val _subHeadDate = mutableStateOf("")
    val subHeadDate: State<String> get() = _subHeadDate

    private val _writeDiaryText = mutableStateOf("")
    val writeDiaryText: State<String> get() = _writeDiaryText

    var sleepTapClicked = mutableStateListOf(false, false, false)

    private val _writeMoodTag = mutableStateOf<String?>(null)
    val writeMoodTag: State<String?> = _writeMoodTag

    var writeShowDialog by mutableStateOf(false)
    var isContinueWriting by mutableStateOf(false)

    /** Read Screen */
    private val _name = mutableStateOf("홍길동")
    val name: State<String> get() = _name

    private val _readDiaryText = mutableStateOf("")
    val readDiaryText: State<String> get() = _readDiaryText

    private val _readMoodTag = mutableStateOf("")
    val readMoodTag: State<String> get() = _readMoodTag

    private val _readSleepTag = mutableStateOf<List<String>>(emptyList())
    val readSleepTag: List<String> get() = _readSleepTag.value

    /** Continue Screen */
    var continueShowDialog by mutableStateOf(false)

    private val _continueSleepTag = mutableStateOf("")
    val continueSleepTag: State<String> get() = _continueSleepTag

    private val _continueConditionTag = mutableStateOf("")
    val continueConditionTag: State<String> get() = _continueConditionTag

    /** Complete Screen */
    private val _comment = mutableStateOf("")
    val comment: State<String> get() = _comment

    private val _slideValue = mutableIntStateOf(0)
    val slideValue: State<Int> get() = _slideValue

    private val _completeSleepTag = mutableStateOf<String>("")
    val completeSleepTag: State<String> get() = _completeSleepTag

    private val _completeConditionTag = mutableStateOf<String>("")
    val completeConditionTag: State<String> get() = _completeConditionTag

    fun setReadDiaryText() {
        _readDiaryText.value = _writeDiaryText.value
    }

    fun setReadMoodTag() {
        _readMoodTag.value = getSelectedMoodTag()
    }

    fun setReadSleepTag(tag: List<String>) {
        _readSleepTag.value = tag
    }

    fun setWriteDiaryText(text: String) {
        _writeDiaryText.value = text
    }

    fun setSelectedDate(date: String){
        _selectedDate.value = LocalDate.now().format(DateTimeFormatter.ISO_DATE) /** 실제로는 캘린더에서 선택한 날짜를 사용해야 함. */

        _TopbarDate.value = formatDateToShortDay(_selectedDate.value)
        _subTitleDate.value = formatMonthDayWithDayOfWeek(_selectedDate.value)
        _subHeadDate.value = formatDateToMonthDay(_selectedDate.value)
    }

    fun selectMoodTag(tag: String?) {
        _writeMoodTag.value = tag
    }

    fun setContinueSleepTag(tag: String) {
        _continueSleepTag.value = tag
    }

    fun setContinueConditionTag(tag: String) {
        _continueConditionTag.value = tag
    }

    fun setComment(comment: String) {
        _comment.value = comment
    }

    fun setCompleteSleepTag(tag: String) {
        _completeSleepTag.value = tag
    }

    fun setCompleteConditionTag(tag: String) {
        _completeConditionTag.value = tag
    }

    fun setSlideValue(value: Int) {
        _slideValue.value = value
    }

    /** "2024-01-01" -> "1월 1일 월" 변환 함수 */
    private fun formatDateToShortDay(originalDateString: String): String {
        // 입력 형식 정의
        val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        // 날짜 문자열 파싱
        val date = LocalDate.parse(originalDateString, inputFormatter)

        // 월과 일을 추출
        val month = date.month.getDisplayName(TextStyle.SHORT, Locale.KOREAN)
        val day = date.dayOfMonth

        // 요일을 추출하여 첫 글자만 사용
        val dayOfWeek = date.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.KOREAN).first()

        // 포맷된 문자열 반환
        return "$month ${day}일 $dayOfWeek"
    }

    /** "2024-01-01" -> "1월 1일 월요일" 변환 함수 */
    private fun formatMonthDayWithDayOfWeek(inputDate: String): String {
        // 입력 형식 정의
        val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        // 날짜 문자열 파싱
        val date = LocalDate.parse(inputDate, inputFormatter)

        // 출력 형식 정의
        val month = date.month.getDisplayName(TextStyle.SHORT, Locale.KOREAN)
        val day = date.dayOfMonth
        val dayOfWeek = date.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.KOREAN)

        return "$month ${day}일 $dayOfWeek"
    }

    /** "2024-01-01" -> "1월 1일" 변환 함수 */
    private fun formatDateToMonthDay(originalDateString: String): String {
        // 입력 형식 정의
        val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

        // 날짜 문자열 파싱
        val date = LocalDate.parse(originalDateString, inputFormatter)

        // 월과 일 추출
        val month = date.monthValue
        val day = date.dayOfMonth

        // 포맷된 문자열 반환
        return "${month}월 ${day}일"
    }

    /** 수면 방해 버튼 중 선택한 버튼 반환 */
    fun getSelectedDisturbTag(): List<String> {
        return getSelectedIndices().map { index ->
            when (index) {
                0 -> "과한 운동을"
                1 -> "과음을"
                2 -> "카페인 섭취를"
                else -> ""
            }
        }
    }

    /** 수면 방해 버튼 중 선택한 버튼 인덱스 반환*/
    fun getSelectedIndices(): List<Int> {
        return sleepTapClicked.mapIndexedNotNull { index, isSelected ->
            if (isSelected) index else null
        }
    }

    /** 선택한 기분 태그 변환 */
    fun getSelectedMoodTag(): String {
        return when (_writeMoodTag.value) {
            "행복해요" -> "행복한"
            "편안해요" -> "편안한"
            "내일이 기대돼요" -> "내일이 기대되는"
            "잠이 솔솔와요" -> "잠이 솔솔오는"
            "그냥 그래요" -> "그냥 그런"
            "슬퍼요" -> "슬픈"
            "지쳤어요" -> "지치는"
            "걱정이 많아요" -> "걱정이 많은"
            "불안해요" -> "불안한"
            else -> ""
        }
    }
}

