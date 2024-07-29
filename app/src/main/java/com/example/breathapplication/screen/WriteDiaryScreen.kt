package com.example.breathapplication.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.breathapplication.R
import com.example.breathapplication.component.CompleteButton
import com.example.breathapplication.component.DisturbButton
import com.example.breathapplication.component.InputDiaryText
import com.example.breathapplication.component.MoodTag
import com.example.breathapplication.component.NormalButton
import com.example.breathapplication.component.TobBar
import com.example.breathapplication.navigation.ButtonNavItem
import com.example.breathapplication.ui.theme.Greyscale10
import com.example.breathapplication.ui.theme.Greyscale2
import com.example.breathapplication.ui.theme.Greyscale5
import com.example.breathapplication.ui.theme.Typography2
import com.example.breathapplication.viewmodel.DiaryScreenViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WriteDiaryScreen(navController: NavHostController, diaryScreenViewModel: DiaryScreenViewModel) {
    diaryScreenViewModel.setSelectedDate("")

    Column(
        Modifier
            .background(Color.Black)
    )
    {
        TobBar(title = diaryScreenViewModel.TopbarDate.value, R.drawable.ic_calendar, R.drawable.ic_setting)
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 20.dp, end = 20.dp, top = 24.dp),
        ) {
            item {
                Column {
                    Text(
                        text = diaryScreenViewModel.subTitleDate.value,
                        style = Typography2.subTitle,
                        color = Greyscale5,
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .padding(horizontal = 2.dp)
                            .fillMaxWidth()
                    )
                }

                Spacer(modifier = Modifier.height(3.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(28.dp),
                ) {
                    Text(
                        text = "나의 수면 일기",
                        style = Typography2.h1,
                        color = Greyscale2,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(horizontal = 2.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))

                    Icon(
                        modifier = Modifier
                            .fillMaxHeight(),
                        painter = painterResource(id = R.drawable.ic_sheep),
                        contentDescription = "amount",
                        tint = Color(0xFF5F7CFE),
                    )
                }

                Spacer(modifier = Modifier.height(26.dp))
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 2.dp),
                    text = "오늘 하루를 가볍게 기록해요",
                    color = Greyscale2,
                    style = Typography2.subTitle,
                    lineHeight = 22.sp,
                )

                Spacer(modifier = Modifier.height(14.dp))
                InputDiaryText(diaryScreenViewModel)

                Spacer(modifier = Modifier.height(56.dp))
                Text(
                    text = "수면을 방해할 만한 요인이 있었나요?",
                    style = Typography2.subTitle,
                    color = Greyscale2,
                )

                Spacer(modifier = Modifier.height(16.dp))
                SleepTagList(diaryScreenViewModel)

                Spacer(modifier = Modifier.height(56.dp))
                Text(
                    text = "마지막으로, 지금 나의 기분은?",
                    style = Typography2.subTitle,
                    color = Greyscale2,
                )

                Spacer(modifier = Modifier.height(16.dp))
                MoodTagList(diaryScreenViewModel)

                Spacer(modifier = Modifier.height(76.dp))
                CompleteButton("작성 완료", onClick = {
                    diaryScreenViewModel.writeShowDialog = true
                    diaryScreenViewModel.setReadDiaryText()
                    diaryScreenViewModel.setReadSleepTag(diaryScreenViewModel.getSelectedDisturbTag())
                })
            }
        }

        if (diaryScreenViewModel.writeShowDialog) {
            CompleteDialog1(diaryScreenViewModel, navController)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CompleteDialog1(diaryScreenViewModel: DiaryScreenViewModel, navController : NavHostController){
    Dialog(onDismissRequest = { diaryScreenViewModel.writeShowDialog = false }) {
        Surface(
            color = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .height(368.dp),
            shape = RoundedCornerShape(6.dp)
        ) {
            Column(
                modifier = Modifier
                    .background(Greyscale10) // 배경색 지정
                    .padding(20.dp, 62.dp, 20.dp, 42.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "일기 작성을 마치셨네요!",
                    style = Typography2.body1,
                    color = Greyscale2,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "수면 측정 후 일기를 이어 쓸 수 있어요.\n수면 측정을 바로 시작할까요?",
                    style = Typography2.body1,
                    color = Greyscale2,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(40.dp))

                NormalButton("일기 먼저 볼래요", onClick = {
                    navController.navigate(
                        ButtonNavItem.ReadDiaryScreen.route)
                    diaryScreenViewModel.writeShowDialog = false
                })

                Spacer(modifier = Modifier.height(10.dp))

                CompleteButton("수면 측정 하러가기", onClick = { diaryScreenViewModel.writeShowDialog = false })

            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SleepTagList(diaryScreenViewModel: DiaryScreenViewModel) {
    val tagTextList = listOf("과한 운동", "과음", "카페인")

    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(tagTextList.size) { index ->
            DisturbButton(tag = tagTextList[index], isClicked = diaryScreenViewModel.sleepTapClicked[index]) {
                diaryScreenViewModel.sleepTapClicked[index] = !diaryScreenViewModel.sleepTapClicked[index]
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MoodTagList(diaryScreenViewModel: DiaryScreenViewModel) {
    val tagTextList = listOf(
        "행복해요", "편안해요", "내일이 기대돼요",
        "잠이 솔솔와요", "그냥 그래요", "슬퍼요",
        "지쳤어요", "걱정이 많아요", "불안해요"
    )

    val itemsPerRow = 3
    val rows = tagTextList.chunked(itemsPerRow)

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        rows.forEach { rowItems ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                rowItems.forEach { tag ->
                    MoodTag(tag,
                        isSelected = tag == diaryScreenViewModel.writeMoodTag.value,
                        onClick = {
                            diaryScreenViewModel.selectMoodTag(if (tag == diaryScreenViewModel.writeMoodTag.value) null else tag)
                            diaryScreenViewModel.setReadMoodTag()
                        }
                    )
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showSystemUi = true)
@Composable
fun WriteDiaryScreenPreview() {
    val navController = rememberNavController()
    WriteDiaryScreen(navController, viewModel())
}