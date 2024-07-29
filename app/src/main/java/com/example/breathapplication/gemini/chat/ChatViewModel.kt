package com.example.breathapplication.gemini.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.breathapplication.gemini.chat.data.ChatData
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ChatViewModel: ViewModel() {
    private val _conversation = MutableStateFlow(listOf(ChatData.Message.initConv, ChatData.Message.secondConv))
    val conversation = _conversation.asStateFlow()

    private val _textValue = MutableStateFlow("")
    val textValue = _textValue.asStateFlow()

    private val savedChats = mutableListOf<ChatData.Message>()

    private val fakeQuestions = mutableListOf(
        "성별을 입력해 주세요!",
        "직업을 간단하게 입력해 주세요! (ex: 대학생, 직장인 등)",
        "평소 잠자리에 드는 시간을 입력해 주세요!",
        "평소 일어나는 시간을 입력해 주세요!",
        "주말에 잠자리에 드는 시간과 일어나는 시간을 입력해 주세요!",
        "낮잠을 자는 경우, 하루에 몇 번 자고 얼마나 자는지 입력해 주세요!",
        "잠들기 전에 카페인이나 알코올을 섭취하시나요? 그렇다면 어느 정도 섭취하시나요?",
        "잠자리에 들기 전에 전자 기기를 사용하시나요? 그렇다면 어떤 종류의 기기를 얼마나 사용하시나요?",
        "잠자리에 들기 전에 책을 읽거나 TV를 보거나 다른 활동을 하시나요? 그렇다면 어떤 활동을 얼마나 오랫동안 하시나요?",
        "운동을 하시나요? 그렇다면 주당 몇 번, 얼마나 운동하시나요?",
        "스트레스를 많이 받으시는 편입니까? 그렇다면 어떤 종류의 스트레스를 받으시나요?",
        "잠들기까지 얼마나 걸리는지 입력해 주세요!",
        "밤중에 몇 번 일어나는지 입력해 주세요!",
        "일어난 후 다시 잠들기까지 얼마나 걸리는지 입력해 주세요!",
        "아침에 기분이 어떠신가요? (예: 상쾌함, 피곤함, 무기력함 등)",
        "낮에 졸음을 느끼시나요? 그렇다면 얼마나 자주 졸음을 느끼시나요?",
        "충분한 수면을 취했다고 느끼시나요?",
        "최근 1개월 동안 수면 패턴에 변화가 있었나요? 그렇다면 어떤 변화가 있었나요?",
        "만성 질환이 있으신가요? 그렇다면 어떤 질환이 있으신가요?",
        "최근 1개월 동안 약을 복용하셨나요? 그렇다면 어떤 약을 복용하셨나요?",
        "정신 건강 문제로 진단을 받은 적이 있으신가요? 그렇다면 어떤 문제로 진단을 받으셨나요?"
    )

    fun sendChat(msg: String) {
        val myChat = ChatData.Message(
            text = msg,
            author = ChatData.Author.me
        )

        viewModelScope.launch {

            savedChats.add(myChat)

            _conversation.emit(_conversation.value + myChat)

            delay(1000)

            _conversation.emit(_conversation.value + getQuestion())
        }
    }

    private fun getQuestion(): ChatData.Message {
        val question = if(fakeQuestions.isEmpty()) {
            "수고했습니다! 답변을 모두 입력하셨습니다. 결과를 확인하시겠습니까?\n ${savedChats.joinToString("\n") { it.text }}"
        } else {
            fakeQuestions.removeAt(0)
        }

        return ChatData.Message(
            text = question,
            author = ChatData.Author.bot
        )
    }

    fun getText( newText:String ) {
        _textValue.update {
            newText
        }
    }
}