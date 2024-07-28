package com.example.breathapplication.gemini.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.breathapplication.ui.theme.Greyscale10
import com.example.breathapplication.ui.theme.Greyscale6
import com.example.breathapplication.ui.theme.Greyscale8
import com.example.breathapplication.ui.theme.Primary1
import com.example.breathapplication.ui.theme.Typography2
import kotlinx.coroutines.launch

@Composable
fun ChatScreen(
    viewModel: ChatViewModel
) {
    val conversation by viewModel.conversation.collectAsStateWithLifecycle()
    val textField by viewModel.textValue.collectAsStateWithLifecycle()

    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize().imePadding()
    ) {

        LaunchedEffect(conversation.size) {
            coroutineScope.launch {
                listState.animateScrollToItem(conversation.size)
            }
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize().weight(1f),
            state = listState
        ) {
            items(conversation) { item ->
                ChatItem(message = item)
            }
        }

        Box(
            modifier = Modifier
        ) {
            ChatTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 50.dp),
                value = textField,
                onValueChange = { viewModel.getText(it) },
                onSendClickListener = { msg ->
                    viewModel.sendChat(msg)
                }
            )
        }
    }
}


@Composable
fun ChatTextField(
    modifier: Modifier,
    onSendClickListener: (String) -> Unit,
    value : String,
    onValueChange: (String) -> Unit
) {
    //var text by remember { mutableStateOf("") }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(52.dp)
    ) {
        TextField(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(6.dp, 0.dp, 0.dp, 6.dp))
                .border(1.dp, Greyscale8, RoundedCornerShape(6.dp, 0.dp, 0.dp, 6.dp))
                .weight(1f),
            value = value,
            onValueChange = onValueChange,
            shape = RoundedCornerShape(6.dp, 0.dp, 0.dp, 6.dp),
            colors = TextFieldDefaults.colors(
                disabledContainerColor = Greyscale10,
                unfocusedContainerColor = Greyscale10,
                focusedContainerColor = Greyscale10,
                focusedIndicatorColor = Color.Transparent,
            ),
            placeholder = {
                Text(
                    text = "텍스트 입력",
                    style = Typography2.bodyText,
                    color = Greyscale6
                )
            },
            textStyle = Typography2.bodyText.copy(color = Greyscale6),
            maxLines = 1,
        )

        Box(
            modifier = Modifier
                .size(73.dp, 52.dp)
                .clip(RoundedCornerShape(0.dp, 6.dp, 6.dp, 0.dp))
                .background(Primary1)
                .clickable {
                    onSendClickListener(value)
                    onValueChange("")
                }
        ) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = "전송",
                style = Typography2.button,
                color = Color.White,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ChatTextFieldPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp),
        verticalArrangement = Arrangement.Center
    ) {
        ChatTextField(
            modifier = Modifier.fillMaxWidth(),
            onSendClickListener = {},
            value = "",
            onValueChange = {}
        )
    }
}