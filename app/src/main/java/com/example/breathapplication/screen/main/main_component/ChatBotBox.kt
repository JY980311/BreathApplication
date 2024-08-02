package com.example.breathapplication.screen.main.main_component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.example.breathapplication.R
import com.example.breathapplication.ui.theme.Greyscale1
import com.example.breathapplication.ui.theme.Greyscale10
import com.example.breathapplication.ui.theme.Greyscale4
import com.example.breathapplication.ui.theme.Greyscale5
import com.example.breathapplication.ui.theme.Greyscale7
import com.example.breathapplication.ui.theme.Greyscale9
import com.example.breathapplication.ui.theme.Primary1
import com.example.breathapplication.ui.theme.Primary2
import com.example.breathapplication.ui.theme.Typography2

/** AI 기반의 챗봇을 보여주는 컴포저블 */
@Composable
fun ChatBotBox(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .height(200.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Greyscale10)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Row(
                modifier = Modifier
                    .padding(horizontal = 18.dp)
                    .padding(top = 18.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                Image(
                    painter = painterResource(id = R.drawable.ic_sheepbot),
                    contentDescription = ""
                )

                Box(
                    modifier = Modifier
                        .clip(
                            RoundedCornerShape(
                                topStart = 2.dp,
                                topEnd = 12.dp,
                                bottomStart = 12.dp,
                                bottomEnd = 12.dp
                            )
                        )
                        .background(Greyscale9)
                        .border(
                            1.dp,
                            Greyscale7,
                            RoundedCornerShape(
                                topStart = 2.dp,
                                topEnd = 12.dp,
                                bottomStart = 12.dp,
                                bottomEnd = 12.dp
                            )
                        )
                        .padding(vertical = 14.dp, horizontal = 22.dp)
                ) {
                    Text(
                        text = buildAnnotatedString {
                            append("AI 기반의 ")
                            withStyle(SpanStyle(color = Primary2)) { append("챗봇") }
                            append("에게 수면 상담을 받을 수도 있어요! 한 번 저랑 ")
                            withStyle(SpanStyle(color = Primary2)) { append("대화") }
                            append("를 해보실래요?\n")
                            withStyle(SpanStyle(color = Primary2)) { append("설문 형식") }
                            append("으로 진행되니 편하게 설문 내용에 대해서 얘기 해주세요!")
                        },
                        style = Typography2.bodyText,
                        color = Greyscale5
                    )
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(Greyscale9)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        modifier = Modifier.padding(vertical = 14.dp, horizontal = 22.dp),
                        text = "좋아요! 저의 얘기를 들어주세요!",
                        style = Typography2.bodyText,
                        color = Greyscale4
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(78.dp)
                            .background(Primary1)
                            .clickable {
                                //TODO : 눌렀을 때 수면 상담 스크린 연결하기
                            }
                    ) {
                        Text(
                            modifier = Modifier.align(Alignment.Center),
                            text = "전송",
                            style = Typography2.button,
                            color = Greyscale1,
                        )
                    }
                }
            }
        }
    }
}