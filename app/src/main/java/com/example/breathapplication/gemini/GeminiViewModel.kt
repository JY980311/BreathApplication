package com.example.breathapplication.gemini

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.breathapplication.BuildConfig
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class GeminiViewModel: ViewModel() {
    private val generativeModel = GenerativeModel(
        modelName = "gemini-pro",
        apiKey = BuildConfig.GOOGLE_AI_API_KEY
    )

    private val _textGenerationResult = MutableStateFlow<String?>(null)
    val textGenerationResult = _textGenerationResult.asStateFlow()

    val chat = generativeModel.startChat(
        history = listOf(
            content(role = "user") { text("너는 수면 패턴을 분석해주는 전문적인 상담가야") },
            content(role = "model") { text("좋습니다! 항상 부드럽게 말을 하면서 수면 패턴을 분석해드릴게요.") }
        )
    )

    fun generateText(prompt: String = "요즘 잠을 너무 못 자 ㅠㅠ") {
        _textGenerationResult.value = "스토리 생성 중"


        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = generativeModel.generateContent(prompt)
                val result2 = chat.sendMessage(prompt)
                _textGenerationResult.value = result2.text
            } catch (e: Exception) {
                _textGenerationResult.value = "스토리 생성 실패"
            }
        }
    }
}