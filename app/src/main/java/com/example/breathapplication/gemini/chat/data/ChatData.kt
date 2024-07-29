package com.example.breathapplication.gemini.chat.data

import android.os.Message
import com.example.breathapplication.R

data class ChatData(
    val message: List<Message>,
    val address: Author
) {
    data class Message(
        val text: String,
        val author: Author,
        val icon: Int? = null
    ) {
        val isFromMe: Boolean
            get() = author.id == MY_ID

        companion object {
            val initConv =  Message(
                text = "안녕하세요! 수면 패턴을 분석하기 위해서 ${Questions.questions.size}개의 질문을 할 예정입니다! 바로 시작할게요!",
                author = Author.bot,
                icon = R.drawable.ic_sheepbot
            )
            val secondConv = Message(
            text = "${Questions.questions[0].id}. ${Questions.questions[0].text}",
                author = Author.bot
            )
        }
    }

    data class Author(
        val id: String,
        val name: String
    ) {
        companion object {
            val bot = Author("1", "bot")
            val me = Author(MY_ID, "ME")
        }
    }

    companion object {
        const val MY_ID = "-1"
    }
}


