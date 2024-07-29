package com.example.breathapplication.gemini.chat

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.breathapplication.gemini.chat.data.ChatData
import com.example.breathapplication.ui.theme.Greyscale4
import com.example.breathapplication.ui.theme.Greyscale9
import com.example.breathapplication.ui.theme.Typography2

@Composable
fun ChatItem(
    message: ChatData.Message
) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {

        message.icon?.let {
            Image(
                imageVector = ImageVector.vectorResource(id = message.icon),
                contentDescription = ""
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = if(message.icon != null) 16.dp else 64.dp, bottom = 8.dp)
        ) {
            Box(
                modifier = Modifier
                    .align(if (message.isFromMe) Alignment.End else Alignment.Start)
                    .widthIn(max = 300.dp)
                    .clip(
                        RoundedCornerShape(
                            topStart = if (message.isFromMe) 12.dp else 2.dp,
                            topEnd = if (message.isFromMe) 2.dp else 12.dp,
                            bottomEnd = 12.dp,
                            bottomStart = 12.dp
                        )
                    )
                    .background(Greyscale9)
                    .padding(horizontal = 22.dp, vertical = 14.dp)
            ) {
                Text(
                    text = message.text,
                    style = Typography2.bodyText,
                    color = Greyscale4
                )
            }
        }
    }
}