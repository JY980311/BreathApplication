package com.example.breathapplication.screen.sleep

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
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
import com.example.breathapplication.component.CompleteButton
import com.example.breathapplication.component.NormalButton
import com.example.breathapplication.ui.theme.Greyscale11
import com.example.breathapplication.ui.theme.Greyscale5
import com.example.breathapplication.ui.theme.Primary2
import com.example.breathapplication.ui.theme.Typography2

@Composable
fun SleepIngScreen() {
    Column(
        modifier = Modifier
            .background(color = Greyscale11)
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.safeDrawing)
            .padding(start = 20.dp, end = 20.dp)
    ) {
        Spacer(modifier = Modifier.height(64.dp))

        Icon(
            painter = painterResource(id = R.drawable.ic_sheeps),
            contentDescription = "sheeps",
            tint = Color.Unspecified,
            modifier = Modifier
                .padding(top = 5.dp)
                .align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(10.dp))

        Text(
            "홍길동님의 수면을 측정하는 중...",
            style = Typography2.subHead,
            color = Primary2,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(30.dp))

        Icon(
            painter = painterResource(id = R.drawable.ic_wavy_line),
            contentDescription = "wavy",
            tint = Color.Unspecified,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(65.dp))

        Icon(
            painter = painterResource(id = R.drawable.ic_sleep_ing),
            contentDescription = "sleeping",
            tint = Color.Unspecified,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(89.dp))

        CompleteButton(text = "일어나기", onClick = {})
        Spacer(modifier = Modifier.height(10.dp))

        NormalButton(text = "측정 중단하기", onClick = {})
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            "측정을 중단할 경우 모든 기록이 사라져요.",
            style = Typography2.subTitle,
            color = Greyscale5,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showSystemUi = true)
@Composable
fun SleepIngScreenPreview() {
    SleepIngScreen()
}