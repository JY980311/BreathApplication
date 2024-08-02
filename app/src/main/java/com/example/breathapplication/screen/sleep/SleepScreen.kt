package com.example.breathapplication.screen.sleep

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.breathapplication.R
import com.example.breathapplication.component.CompleteButton
import com.example.breathapplication.navigation.sleep.SleepNavItem
import com.example.breathapplication.ui.theme.Greyscale11
import com.example.breathapplication.ui.theme.Greyscale2
import com.example.breathapplication.ui.theme.Greyscale4
import com.example.breathapplication.ui.theme.Greyscale5
import com.example.breathapplication.ui.theme.Primary2
import com.example.breathapplication.ui.theme.Typography2
import com.example.breathapplication.viewmodel.DiaryScreenViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SleepScreen(diaryScreenViewModel: DiaryScreenViewModel, navController: NavHostController) {
    Column(
        Modifier
            .background(color = Greyscale11)
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.safeDrawing)
            .padding(start = 20.dp, end = 20.dp)
    ) {
        Spacer(modifier = Modifier.height(91.dp))
        Text(
            diaryScreenViewModel.subTitleDate.value,
            style = Typography2.h1,
            color = Greyscale2,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(37.dp))
        val text = buildAnnotatedString {
            append("측정이 시작하면, 홍길동님의\n")
            withStyle(style = SpanStyle(color = Primary2)) {
                append("수면 중 호흡")
            }
            append("을 분석할 거예요.")
        }
        Text(
            text,
            style = Typography2.subHead,
            color = Greyscale4,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(38.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ){
            Icon(
                painter = painterResource(id = R.drawable.ic_warning),
                contentDescription = "warning",
                tint = Color.Unspecified,
                modifier = Modifier.padding(top = 5.dp)
            )
            Spacer(modifier = Modifier.width(5.dp))

            Text(
                "측정 도중 전원이 꺼지지 않도록\n주의해주세요.",
                style = Typography2.subHead,
                color = Greyscale4,
                textAlign = TextAlign.Center,
            )
        }
        Spacer(modifier = Modifier.height(46.dp))

        Icon(
            painter = painterResource(R.drawable.ic_three_circle),
            contentDescription = "circle",
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            tint = Color.Unspecified,
        )
        Spacer(modifier = Modifier.height(76.dp))

        Text(
            "잠에 들 준비가 되셨나요?",
            style = Typography2.subTitle,
            color = Greyscale5,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        CompleteButton(text = "수면 측정 시작하기", onClick = {
            navController.navigate(SleepNavItem.SleepIng.route)
        })
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showSystemUi = true)
@Composable
fun SleepScreenPreview() {
    val navController = rememberNavController()
    SleepScreen(diaryScreenViewModel = DiaryScreenViewModel(), navController = navController)
}