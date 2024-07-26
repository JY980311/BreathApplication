package com.example.breathapplication.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.breathapplication.R
import com.example.breathapplication.component.CompleteButton
import com.example.breathapplication.component.ConditionButton
import com.example.breathapplication.component.MoodTag
import com.example.breathapplication.component.SlideButton
import com.example.breathapplication.component.TobBar
import com.example.breathapplication.ui.theme.Greyscale10
import com.example.breathapplication.ui.theme.Greyscale2
import com.example.breathapplication.ui.theme.Typography2
import com.example.breathapplication.viewmodel.ContinueDiaryScreenViewModel
import com.example.breathapplication.viewmodel.ReadDiaryScreenViewModel
import com.example.breathapplication.viewmodel.WriteDiaryScreenViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ContinueDiaryScreen(writeViewModel: WriteDiaryScreenViewModel , readViewModel: ReadDiaryScreenViewModel, viewModel:ContinueDiaryScreenViewModel){
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
        ){
            item {
                MyDiaryScreen(readViewModel = readViewModel, writeViewModel = writeViewModel)
            }
            item{
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

                    Spacer(modifier = Modifier.height(25.dp))

                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "수면 도중 어떤 일이 일어났나요?",
                        style = Typography2.subTitle,
                        color = Greyscale2
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    SleepDisturbTag()

                    Spacer(modifier = Modifier.height(52.dp))

                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "잠에서 깬 직후 어떤 상태였나요?",
                        style = Typography2.subTitle,
                        color = Greyscale2
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    ConditionTag()
                    Spacer(modifier = Modifier.height(54.dp))
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "오늘 나의 수면 만족도는?",
                        style = Typography2.subTitle,
                        color = Greyscale2
                    )
                    Spacer(modifier = Modifier.height(35.dp))
                    SlideButton()
                    Spacer(modifier = Modifier.height(38.dp))
                    CompleteButton(text = "작성 완료", onClick = {viewModel.showDialog = true})
                }

            }

        }
    }
    if(viewModel.showDialog){
        CompleteDialog2(viewModel = viewModel, writeViewModel = writeViewModel, readViewModel)
    }
}

@Composable
fun ConditionTag(){
    val tagList = listOf("개운했어요","피곤했어요")

    val itemsPerRow = 2
    val rows = tagList.chunked(itemsPerRow)

    var selectedTag by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        rows.forEach { rowItems ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                rowItems.forEach { tag ->
                    ConditionButton(tag,
                        isSelected = tag == selectedTag,
                        onClick = {
                            selectedTag = if (tag == selectedTag) null else tag
                        })
                }
            }
        }
    }

}

@Composable
fun SleepDisturbTag(){
    val tagList = listOf("푹 잤어요","뒤척였어요","자주 깼어요","그냥 잤어요", "꿈을 많이 꿨어요")

    val itemsPerRow = 3
    val rows = tagList.chunked(itemsPerRow)

    var selectedTag by remember { mutableStateOf<String?>(null) }

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
                        isSelected = tag == selectedTag,
                        onClick = {
                            selectedTag = if (tag == selectedTag) null else tag
                        }
                        )
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CompleteDialog2(viewModel: ContinueDiaryScreenViewModel, writeViewModel: WriteDiaryScreenViewModel, readViewModel: ReadDiaryScreenViewModel){
    Dialog(onDismissRequest = { viewModel.showDialog = false },) {
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
                Icon(
                    painter = painterResource(id = R.drawable.ic_sheep),
                    contentDescription = "amount",
                    tint = Color(0xFF5F7CFE),
                )
                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "일기를 모두 완성했어요!",
                    style = Typography2.body1,
                    color = Greyscale2,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "완성된 일기는 나의 수면을 되돌아보는 데",
                    style = Typography2.body1,
                    color = Greyscale2,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "도움이 될 거에요.\n숨소리가 ${readViewModel.name.value}님의 꿀잠을",
                    style = Typography2.body1,
                    color = Greyscale2,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "응원할게요!",
                    style = Typography2.body1,
                    color = Greyscale2,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(40.dp))

                CompleteButton("완성된 일기 보러가기", onClick = { viewModel.showDialog = false })

            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showSystemUi = true)
@Composable
fun ContinueDiaryScreenPreview() {
    ContinueDiaryScreen(writeViewModel = WriteDiaryScreenViewModel(), readViewModel= ReadDiaryScreenViewModel(), viewModel = ContinueDiaryScreenViewModel())
}