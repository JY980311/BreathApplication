package com.example.breathapplication.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.breathapplication.R
import com.example.breathapplication.component.TobBar
import com.example.breathapplication.ui.theme.Greyscale10
import com.example.breathapplication.ui.theme.Greyscale2
import com.example.breathapplication.ui.theme.Greyscale5
import com.example.breathapplication.ui.theme.Primary1
import com.example.breathapplication.ui.theme.Primary2
import com.example.breathapplication.ui.theme.Typography2
import com.example.breathapplication.viewmodel.CompleteDiaryScreenViewModel
import com.example.breathapplication.viewmodel.ReadDiaryScreenViewModel
import com.example.breathapplication.viewmodel.WriteDiaryScreenViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CompleteDiaryScreen(readViewModel: ReadDiaryScreenViewModel, writeViewModel: WriteDiaryScreenViewModel, viewModel: CompleteDiaryScreenViewModel) {
    Column(
        Modifier
            .background(Color.Black)
            .fillMaxWidth()
    ) {
        TobBar(title = writeViewModel.TopbarCrrentDate)
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 20.dp, end = 20.dp, top = 24.dp),
        ) {
            item {
                MyDiaryScreen(readViewModel = readViewModel, writeViewModel = writeViewModel)
            }
            item {
                Column {
                    Spacer(modifier = Modifier.height(25.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_wavy_line),
                            contentDescription = "물결 아이콘",
                            tint = Color.Unspecified,
                        )
                    }
                    Spacer(modifier = Modifier.height(33.dp))
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "오늘 밤에는",
                        style = Typography2.subHead,
                        color = Greyscale2
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    MyMoodTag(listOf("푹 잤어요"))
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "그리고 잠에서 깼을 땐",
                        style = Typography2.subHead,
                        color = Greyscale2
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,

                    ){
                        Icon(
                            painter = painterResource(R.drawable.ic_fresh_blue),
                            contentDescription = "기분 아이콘",
                            tint = Color.Unspecified,
                        )
                        Spacer(modifier = Modifier.width(14.dp))
                        MySleepTag(tags = listOf("개운했어요!"))
                    }
                    Spacer(modifier = Modifier.height(36.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_wavy_line),
                            contentDescription = "물결 아이콘",
                            tint = Color.Unspecified,
                        )
                    }
                    Spacer(modifier = Modifier.height(37.dp))
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "오늘 나의 수면 만족도는?",
                        style = Typography2.subHead,
                        color = Greyscale2,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_slide_3),
                            contentDescription = "슬라이드",
                            tint = Color.Unspecified,
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "SOSO!",
                        style = Typography2.h1,
                        color = Primary1,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(38.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_wavy_line),
                            contentDescription = "물결 아이콘",
                            tint = Color.Unspecified,
                        )
                    }
                    Spacer(modifier = Modifier.height(35.dp))
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "오늘의 잠을 요약하는 마지막 한마디",
                        style = Typography2.body1,
                        color = Greyscale5,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(30.dp))
                    Box(
                        modifier = Modifier.fillMaxWidth(), // 부모의 크기를 가득 채움
                        contentAlignment = Alignment.Center // 자식 요소를 가운데 정렬
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_sheep),
                            contentDescription = "amount",
                            tint = Color(0xFF5F7CFE),
                        )
                    }
                    Spacer(modifier = Modifier.height(30.dp))

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp)
                            .background(Greyscale10, RoundedCornerShape(6.dp))
                            .border(1.dp, Greyscale10, RoundedCornerShape(6.dp)),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        BasicTextField(
                            modifier = Modifier.padding(start = 24.dp),
                            value = viewModel.text,
                            onValueChange = { viewModel.text = it },
                            textStyle = Typography2.bodyText.copy(color = Primary2),
                            decorationBox = { innerTextField ->
                                Text(
                                    text = if (viewModel.text.equals("")) "마지막 한마디" else "",
                                    color = Primary2,
                                    style = Typography2.bodyText,
                                )
                                innerTextField()
                            })
                    }

                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showSystemUi = true)
@Composable
fun CompleteDiaryScreenPreview() {
    CompleteDiaryScreen(writeViewModel = WriteDiaryScreenViewModel(), readViewModel= ReadDiaryScreenViewModel(), viewModel = CompleteDiaryScreenViewModel())
}
