package com.example.breathapplication.gemini.chat

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.breathapplication.BuildConfig
import com.example.breathapplication.R
import com.example.breathapplication.gemini.chat.data.ChatData
import com.example.breathapplication.gemini.chat.data.Questions
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ChatViewModel : ViewModel() {

    private val _conversation =
        MutableStateFlow(listOf(ChatData.Message.initConv, ChatData.Message.secondConv))
    val conversation = _conversation.asStateFlow()

    /** 제미나이한테 답변을 보내고 받으면 앞에 있는 대화 데이터랑 합쳐서 보여주는 함수 */
    private val _textGenerationResult = MutableStateFlow<String?>(null)
    val textGenerationResult = _textGenerationResult.asStateFlow()

    /** 사용자가 입력한 텍스트 */
    private val _textValue = MutableStateFlow("")
    val textValue = _textValue.asStateFlow()

    /** 저장된 채팅 데이터 */
    private val savedChats = mutableListOf<ChatData.Message>()

    /** 가짜 질문 데이터 */
    private val fakeQuestions =
        Questions.questions.subList(1, Questions.questions.size).map { index ->
            "${index.id}. ${index.text}"
        }.toMutableList()

    /** 저장된 채팅 데이터를 Prompt에 질문 할 형식으로 만들어줌 */
    fun generatePrompt(): String {
        return Questions.questions.mapIndexed { index, question ->
            "${question.id}. ${question.text} : ${savedChats.getOrNull(index)?.text ?: "정보없음"}"
        }.joinToString("\n")
    }

    /** 사용자 채팅을 보내면 작동하는 부분 */
    fun sendChat(msg: String) {
        val myChat = ChatData.Message(
            text = msg,
            author = ChatData.Author.me
        )

        viewModelScope.launch {

            savedChats.add(myChat) // 사용자가 입력한 채팅을 저장

            _conversation.emit(_conversation.value + myChat)

            delay(1000)

            _conversation.emit(_conversation.value + getQuestion())
        }
    }

    /** 제미나이한테 답변을 보내고 받으면 앞에 있는 대화 데이터랑 합쳐서 보여주는 함수 */
    fun geminiSendChat(msg: String) {
        val myChat = ChatData.Message(
            text = msg,
            author = ChatData.Author.me
        )

        viewModelScope.launch {

            _conversation.emit(_conversation.value + myChat)

            delay(1000)

            _conversation.emit(_conversation.value + generateText())
        }
    }

    /** 가짜 질문 데이터를 순서대로 보내주고 마지막에 말을 하는 부분 */
    private fun getQuestion(): ChatData.Message {
        val question = if (fakeQuestions.isEmpty()) {
            "수고했습니다! 수면 패턴 분석을 진행하고 싶으시면 '좋습니다' 라고 입력해주시고 처음부터 다시하고 싶으시면 '다시하기' 라고 입력해주세요"
        } else {
            fakeQuestions.removeAt(0)
        }

        return ChatData.Message(
            text = question,
            author = ChatData.Author.bot,
            icon = R.drawable.ic_sheepbot
        )
    }

    /** 사용자가 입력한 텍스트를 가져오는 함수 */
    fun getText(newText: String) {
        _textValue.update {
            newText
        }
    }

    /** 다시할게요 라고 입력하면 모든 대화내용 초기화 하고 차음부터 다시 질문 */
    fun resetChat() {
        savedChats.clear()
        fakeQuestions.clear()
        fakeQuestions.addAll(Questions.questions.subList(1, Questions.questions.size).map { index ->
            "${index.id}. ${index.text}"
        }.toMutableList())
        _conversation.value = (listOf(ChatData.Message.initConv, ChatData.Message.secondConv))
    }


    /** 제미나이 생성 하는 부분 */
    private val generativeModel = GenerativeModel(
        modelName = "gemini-pro",
        apiKey = BuildConfig.GOOGLE_AI_API_KEY
    )

    /** 제미나이한테 답변을 보내기 전에 히스토리를 통해 더해져서 보내게 해주는 부분 */
    val chat = generativeModel.startChat(
        history = listOf(
            content(role = "user") { text("너는 수면 패턴을 분석해주는 상담가야. 내가 형식에 맞게 대답을 보내줄게 해당 대답을 통해 전문적으로 분석해주고 답을 정확하게 해줘") },
            content(role = "model") { text("좋습니다! 형식에 맞는 답을 보내주시면 제가 전문적으로 분석을 하겠습니다.") }
        )
    )

    /** 제미나이한테 생성된 prompt를 통해 보내는 함수 */
    fun generateText(): ChatData.Message {
        _textGenerationResult.value = "수면 패턴을 분석 중 입니다. 잠시만 기다려주세요."

        val prompt = generatePrompt()

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = chat.sendMessage(prompt)
                _textGenerationResult.value = result.text
            } catch (e: Exception) {
                _textGenerationResult.value = "스토리 생성 실패"
            }
        }

        return ChatData.Message(
            text = _textGenerationResult.value!!,
            author = ChatData.Author.bot,
            icon = R.drawable.ic_sheepbot
        )
    }

    /** 제미나이한테 답변을 보내고 받으면 앞에 있는 대화 데이터랑 합쳐서 보여주는 함수 */
    fun addBotMessage(message: String) {
        val botChat = ChatData.Message(
            text = message,
            author = ChatData.Author.bot,
        )
        viewModelScope.launch {
            _conversation.emit(_conversation.value + botChat)
        }
    }
}