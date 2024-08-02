package com.example.breathapplication.viewmodel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.breathapplication.network.model.supabase.GetSupabaseData
import com.example.breathapplication.network.model.supabase.PostSupabaseData
import com.example.breathapplication.network.retrofit.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
class DiaryScreenViewModel : ViewModel() {
    var isCalendarClicked = mutableStateOf(false)
    var isComplete = mutableStateOf(false)
    var isDiary = mutableStateOf(false)

    /** Write Screen */
    private val _selectedDate = MutableStateFlow(LocalDate.now().format(DateTimeFormatter.ISO_DATE))
    val selectedDate: StateFlow<String> get() = _selectedDate

    private val _TopbarDate = mutableStateOf(formatDateToShortDay(_selectedDate.value))
    val TopbarDate: State<String> get() = _TopbarDate

    private val _subTitleDate = mutableStateOf(formatMonthDayWithDayOfWeek(_selectedDate.value))
    val subTitleDate: State<String> get() = _subTitleDate

    private val _subHeadDate = mutableStateOf(formatDateToMonthDay(_selectedDate.value))
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

    private val _readDiaryText = MutableStateFlow("")
    val readDiaryText: StateFlow<String> get() = _readDiaryText

    private val _readMoodTag = MutableStateFlow("")
    val readMoodTag: StateFlow<String> get() = _readMoodTag

    private val _readSleepTag = MutableStateFlow<String>("")
    val readSleepTag: StateFlow<String> get() = _readSleepTag

    /** Continue Screen */
    var continueShowDialog by mutableStateOf(false)

    private val _continueSleepTag = mutableStateOf("")
    val continueSleepTag: State<String> get() = _continueSleepTag

    private val _continueConditionTag = mutableStateOf("")
    val continueConditionTag: State<String> get() = _continueConditionTag

    /** Complete Screen */
    private val _comment = mutableStateOf("")
    val comment: State<String> get() = _comment

    private val _slideValue = MutableStateFlow(1)
    val slideValue: StateFlow<Int> get() = _slideValue

    private val _completeSleepTag = MutableStateFlow<String>("")
    val completeSleepTag: StateFlow<String> get() = _completeSleepTag

    private val _completeConditionTag = MutableStateFlow<String>("")
    val completeConditionTag: StateFlow<String> get() = _completeConditionTag

    fun setReadDiaryText() {
        _readDiaryText.value = _writeDiaryText.value
    }

    fun setReadMoodTag() {
        _readMoodTag.value = getSelectedMoodTag()
    }

    fun setReadSleepTag(tag: String) {
        _readSleepTag.value = tag
    }

    fun setWriteDiaryText(text: String) {
        _writeDiaryText.value = text
    }

    fun setSelectedDate(date: String){
        _selectedDate.value = date
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
    fun getSelectedDisturbTag(): String {
        val selectedIndex = getSelectedIndices().firstOrNull()
        return when (selectedIndex) {
                0 -> "과한 운동을"
                1 -> "과음을"
                2 -> "카페인 섭취를"
                else -> ""
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

    /** Supabase Post */
    fun postApi(data: PostSupabaseData){

        viewModelScope.launch {
            val apiService = RetrofitClient.getSupabaseApiService()
            try {
                val apiResponse: Response<List<PostSupabaseData>> = apiService.createPost(
                    postSupabaseData = data
                )
                Log.d("데이터 전송", "$apiResponse")
                if(apiResponse.code() == 409){
                    updatePost(data)
                }
            } catch (e: Exception) {
                Log.d("PostViewModel", "error: $e")
            }
        }
    }

    fun postApiTest(){
        val tag2 : Int
        tag2 = when(_readMoodTag.value){
            "행복한" -> 1
            "편안한" -> 2
            "내일이 기대되는" -> 3
            "잠이 솔솔오는" -> 4
            "그냥 그런" -> 5
            "슬픈" -> 6
            "지치는" -> 7
            "걱정이 많은" -> 8
            "불안한" -> 9
            else -> 0
        }

        val numericString = selectedDate.value.replace("-", "")
        val numericDate = numericString.toInt()

        val tag3 : Int
        tag3 = when(_continueConditionTag.value){
            "개운했어요" -> 1
            "피곤했어요" -> 2
            else -> 0
        }

        val newData =  PostSupabaseData(
            id = numericDate,
            created_at = selectedDate.value,
            content = _readDiaryText.value,
            tag_1 = getSelectedIndices().first(),
            tag_2 = tag2,
            tag_3 = tag3,
            graph = _slideValue.value
        )

        Log.d("데이터", "$newData")

        postApi(newData)
    }


    fun getPostById() {
        val numericString = selectedDate.value.replace("-", "")
        val numericDate = numericString.toInt()

        viewModelScope.launch {
            val apiService = RetrofitClient.getSupabaseApiService()
            try {
                val apiResponse = apiService.getPostById(
                    id = "eq.$numericDate"
                )
                Log.d("데이터 통신", "$apiResponse")

                if(apiResponse.isNotEmpty()){
                    isDiary.value = false
                    _readDiaryText.value = apiResponse[0].content
                    _readMoodTag.value = when(apiResponse[0].tag_2){
                        1 -> "행복한"
                        2 -> "편안한"
                        3 -> "내일이 기대되는"
                        4 -> "잠이 솔솔오는"
                        5 -> "그냥 그런"
                        6 -> "슬픈"
                        7 -> "지치는"
                        8 -> "걱정이 많은"
                        9 -> "불안한"
                        else -> ""
                    }
                    _readSleepTag.value = when (apiResponse[0].tag_1) {
                        0 -> "과한 운동을"
                        1 -> "과음을"
                        2 -> "카페인 섭취를"
                        else -> ""
                    }
                    _completeConditionTag.value = if(apiResponse[0].tag_3 == 1) "개운했어요" else "피곤했어요"
                    _slideValue.value = apiResponse[0].graph
                }
                else{
                    isDiary.value = true
                }

            } catch (e: Exception) {
                Log.d("데이터 통신 에러", "error: $e")
            }
        }
        getAllPostData()
    }

    fun getAllPostData() {
        viewModelScope.launch {
            val apiService = RetrofitClient.getSupabaseApiService()
            try {
                val apiResponse: List<GetSupabaseData> = apiService.getAllPost()
                Log.d("데이터 조회", "$apiResponse")
            } catch (e: Exception) {
                Log.d("PostViewModel", "error: $e")
            }
        }
    }

    fun updatePost(postSupabaseData: PostSupabaseData) {
        viewModelScope.launch {
            val apiService = RetrofitClient.getSupabaseApiService()
            try {
                val response = apiService.updatePost(
                    id = "eq.${postSupabaseData.id}",
                    postSupabaseData = postSupabaseData
                )
                if (response.isSuccessful) {
                    val updatedPost = response.body()
                    Log.d("업데이트", "$updatedPost")
                } else {
                    Log.d("업데이트","실패")
                }
            } catch (e: Exception) {
                Log.d("업데이트 실패", "$e")
            }
        }
    }

}

