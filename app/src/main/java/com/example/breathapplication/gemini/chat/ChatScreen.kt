package com.example.breathapplication.gemini.chat

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.breathapplication.R
import com.example.breathapplication.component.TobBar
import com.example.breathapplication.ui.theme.Greyscale10
import com.example.breathapplication.ui.theme.Greyscale11
import com.example.breathapplication.ui.theme.Greyscale4
import com.example.breathapplication.ui.theme.Greyscale6
import com.example.breathapplication.ui.theme.Greyscale8
import com.example.breathapplication.ui.theme.Primary1
import com.example.breathapplication.ui.theme.Typography2
import kotlinx.coroutines.launch

/**
 * TODO: 해당 페이지  사소한 디테일 부분 챙기기
 * 1. 분석이 끝나면 다시하기를 누르면 다시 분석을 시작할 수 있다고 안내 후 다시 설문 조사를 진행
 * 2. 텍스트 필드에 입력한 텍스트가 없을 때 전송 버튼을 누르면 아무런 반응이 없어야 한다.
 * */

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ChatScreen(
    viewModel: ChatViewModel,
    navController : NavHostController
) {
    val conversation by viewModel.conversation.collectAsStateWithLifecycle()
    val textField by viewModel.textValue.collectAsStateWithLifecycle()
    val textGenerationResult by viewModel.textGenerationResult.collectAsStateWithLifecycle()

    val interactionSource = remember { MutableInteractionSource() }

    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Greyscale11)
            .imePadding()
            .windowInsetsPadding(WindowInsets.safeDrawing)
    ) {
        TobBar(title = "수면 상담", leftIcon = R.drawable.ic_arrow, navController = navController)

        LazyColumn(
            modifier = Modifier
                .padding(top = 18.dp, start = 20.dp, end = 20.dp)
                .weight(1f),
            state = listState
        ) {
            items(conversation) { item ->
                ChatItem(message = item)
            }
        }

        LaunchedEffect(textGenerationResult) {
            if (textGenerationResult.isNullOrEmpty().not() && textGenerationResult != "수면 패턴을 분석 중 입니다. 잠시만 기다려주세요.") {
                textGenerationResult?.let { viewModel.addBotMessage(it) }
            }
        }

        Box(
            modifier = Modifier
                .height(96.dp)
                .background(color = Color(0x99111215))
        ) {
            ChatTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    ,
                value = textField,
                onValueChange = { viewModel.getText(it) },
                onSendClickListener = { msg ->
                    when {
                        msg == "좋습니다" -> viewModel.geminiSendChat(msg)
                        msg == "다시하기" -> viewModel.resetChat()
                        else -> viewModel.sendChat(msg)
                    }
                },
                interactionSource = interactionSource,
            )
        }

        LaunchedEffect(conversation.size) {
            coroutineScope.launch {
                listState.animateScrollToItem(conversation.size)
            }
        }
    }
}

@Composable
private fun ChatTextField(
    modifier: Modifier,
    onSendClickListener: (String) -> Unit,
    value: String,
    onValueChange: (String) -> Unit,
    interactionSource: MutableInteractionSource,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(20.dp)
            .height(52.dp)
            .shadow(8.dp, RoundedCornerShape(6.dp, 6.dp, 6.dp, 6.dp))
    ) {
        TextField(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(6.dp, 0.dp, 0.dp, 6.dp))
                .border(1.dp, Greyscale8, RoundedCornerShape(6.dp, 0.dp, 0.dp, 6.dp))
                .weight(1f)
            ,
            value = value,
            onValueChange = onValueChange,
            shape = RoundedCornerShape(6.dp, 0.dp, 0.dp, 6.dp),
            colors = TextFieldDefaults.colors(
                disabledContainerColor = Greyscale10,
                unfocusedContainerColor = Greyscale10,
                focusedContainerColor = Greyscale10,
                focusedIndicatorColor = Color.Transparent,
                cursorColor = Greyscale4,
            ),
            placeholder = {
                Text(
                    text = " 텍스트 입력",
                    style = Typography2.bodyText,
                    color = Greyscale6
                )
            },
            textStyle = Typography2.bodyText.copy(color = Greyscale4),
            maxLines = 1,
            interactionSource = interactionSource
        )
        val isEnabled = value.isNotBlank()

        Box(
            modifier = Modifier
                .size(73.dp, 52.dp)
                .clip(RoundedCornerShape(0.dp, 6.dp, 6.dp, 0.dp))
                .background(if (isEnabled) Primary1 else Greyscale8)
                .clickable(enabled = isEnabled) {
                    onSendClickListener(value)
                    onValueChange("")
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "전송",
                style = Typography2.button,
                color = Color.White,
            )
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnrememberedMutableInteractionSource")
@Preview(showBackground = true)
@Composable
fun ChatTextFieldPreview() {
    val navController = rememberNavController()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp),
        verticalArrangement = Arrangement.Center
    ) {
        ChatScreen(viewModel = ChatViewModel(), navController)
    }
}