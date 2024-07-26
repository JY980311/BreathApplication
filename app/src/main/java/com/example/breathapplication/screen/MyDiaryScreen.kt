package com.example.breathapplication.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.example.breathapplication.ui.theme.Greyscale10
import com.example.breathapplication.ui.theme.Greyscale2
import com.example.breathapplication.ui.theme.Greyscale4
import com.example.breathapplication.ui.theme.Greyscale5
import com.example.breathapplication.ui.theme.Typography2
import com.example.breathapplication.viewmodel.ReadDiaryScreenViewModel
import com.example.breathapplication.viewmodel.WriteDiaryScreenViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MyDiaryScreen(readViewModel: ReadDiaryScreenViewModel, writeViewModel: WriteDiaryScreenViewModel){
    Column(
        Modifier
            .background(Color.Black)
            .fillMaxWidth()
    ) {
        Text(
            text = writeViewModel.currentDate,
            style = Typography2.subTitle,
            color = Greyscale5,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .padding(horizontal = 2.dp)
                .fillMaxWidth()
        )

        Spacer(
            modifier = Modifier
                .height(3.dp)
        )

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

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(130.dp)
                .background(Greyscale10, RoundedCornerShape(6.dp))
                .border(1.dp, Greyscale10, RoundedCornerShape(6.dp))
                .padding(16.dp, 23.dp, 18.dp, 23.dp),
            contentAlignment = Alignment.TopStart
        ) {
            Text(
                "오랜만에 운동을 많이 했더니 다리가 후들거린다..",
                style = Typography2.bodyText,
                color = Greyscale4,
            )
        }

        Spacer(modifier = Modifier.height(36.dp))

        Text(
            text = "${readViewModel.name.value}님은 ${writeViewModel.date},",
            style = Typography2.subHead,
            color = Greyscale2,
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically

        ){
            MyMoodTag(listOf("과한 운동을", "과음을"))
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "했고",
                style = Typography2.subHead,
                color = Greyscale2,
                textAlign = TextAlign.Center,
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "잠들기 전 기분은",
            style = Typography2.subHead,
            color = Greyscale2,
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically

        ){
            MySleepTag(listOf("내일이 기대되는"))
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "기분이었어요.",
                style = Typography2.subHead,
                color = Greyscale2,
                textAlign = TextAlign.Center,
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showSystemUi = true)
@Composable
fun MyDiaryScreenPreview() {
    MyDiaryScreen(writeViewModel = WriteDiaryScreenViewModel(), readViewModel= ReadDiaryScreenViewModel())
}
