package com.example.breathapplication.gemini.chat.data

import android.os.Message

data class ChatData(
    val message: List<Message>,
    val address: Author
) {
    data class Message(
        val text: String,
        val author: Author
    ) {
        val isFromMe: Boolean
            get() = author.id == MY_ID

        companion object {
            val initConv =  Message(
                text = "안녕하세요! 수면 패턴을 분석하기 위해서 22개의 질문을 할 예정입니다! 바로 시작할게요!",
                author = Author.bot
            )
            val secondConv = Message(
                text = "나이를 입력해주세요!",
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


