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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.breathapplication.R
import com.example.breathapplication.component.CompleteButton
import com.example.breathapplication.component.MyMoodTag
import com.example.breathapplication.component.MySleepTag
import com.example.breathapplication.component.NormalButton
import com.example.breathapplication.component.TobBar
import com.example.breathapplication.ui.theme.Greyscale5
import com.example.breathapplication.ui.theme.Typography2
import com.example.breathapplication.viewmodel.ReadDiaryScreenViewModel
import com.example.breathapplication.viewmodel.WriteDiaryScreenViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ReadDiaryScreen(navController: NavHostController, writeViewModel: WriteDiaryScreenViewModel = viewModel(), readViewModel: ReadDiaryScreenViewModel = viewModel()) {
    Column(
        Modifier
            .background(Color.Black)
    )
    {
        TobBar(title = writeViewModel.TopbarCrrentDate)
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 20.dp, end = 20.dp, top = 24.dp),
        ) {
            item{
                Column {
                    MyDiaryScreen(readViewModel = readViewModel, writeViewModel = writeViewModel )
                    Spacer(modifier = Modifier.height(41.dp))

                    Text(
                        text = "오늘 하루 있었던 일들이 수면에 어떤 영향을 미칠까요? 수면 측정 후 일기를 이어 쓸 수 있어요",
                        style = Typography2.body1,
                        color = Greyscale5,
                        textAlign = TextAlign.Center,
                    )
                    Spacer(modifier = Modifier.height(14.dp))

                    Box(
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_three_circle),
                            contentDescription = "원 아이콘",
                            tint = Color.Unspecified,
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))

                    CompleteButton("일기 이어서 쓰기", onClick = { writeViewModel.isContinueWriting = true })
                    Spacer(modifier = Modifier.height(10.dp))

                    NormalButton(text = "일기 수정하기", onClick = {})
                }
            }
        }
    }
}

@Composable
fun MyMoodTag(tags: List<String>) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        tags.forEach { tag ->
            MyMoodTag(tag)
        }
    }
}

@Composable
fun MySleepTag(tags: List<String>) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        tags.forEach { tag ->
            MySleepTag(tag)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showSystemUi = true)
@Composable
fun ReadDiaryScreenPreview() {
    val navController = rememberNavController()
    ReadDiaryScreen(navController)
}