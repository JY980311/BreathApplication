package com.example.breathapplication.settingscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.breathapplication.R
import com.example.breathapplication.settingnavigation.SettingNavItem
import com.example.breathapplication.ui.theme.Greyscale10
import com.example.breathapplication.ui.theme.Greyscale11
import com.example.breathapplication.ui.theme.Greyscale2
import com.example.breathapplication.ui.theme.Greyscale8
import com.example.breathapplication.ui.theme.Typography2

@Composable
fun Help(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Greyscale11)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(color = Greyscale10)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.arrow),
                    contentDescription = "arrow",
                    modifier = Modifier
                        .clickable{
                            navController.navigate(SettingNavItem.Setting.route)
                        }

                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "문의하기",
                    style = Typography2.body1,
                    color = Greyscale2,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
                Spacer(modifier = Modifier.weight(1f))
            }
        }
        Spacer(modifier = Modifier.height(110.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "어떤 도움이 필요하신가요?",
                style = Typography2.subHead,
                color = Greyscale2,
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }
        Spacer(modifier = Modifier.height(42.dp))
        HelpItem(text = "기능 추가 요청", offsetY = 0.dp)
        HelpItem(text = "버그 신고", offsetY = 0.dp)
        HelpItem(text = "기타 피드백", offsetY = 0.dp)
        HelpItem(text = "일반 문의", offsetY = 0.dp)
        HelpItem(text = "자주 묻는 질문", offsetY = 0.dp)
    }
}

@Composable
fun HelpItem(text: String, offsetY: Dp) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(58.dp)
            .offset(y = offsetY)
            .background(color = Greyscale10)
            .drawBehind {
                drawLine(
                    color = Greyscale8,
                    strokeWidth = 1.dp.toPx(),
                    start = Offset(0f, 0f),
                    end = Offset(size.width, 0f)
                )
                drawLine(
                    color = Greyscale8,
                    strokeWidth = 1.dp.toPx(),
                    start = Offset(0f, size.height),
                    end = Offset(size.width, size.height)
                )
            },
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.Center),
            text = text,
            style = Typography2.body1,
            color = Greyscale2
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun ShowHelp() {
    /*
    Help()
     */
}