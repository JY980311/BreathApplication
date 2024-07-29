package com.example.breathapplication.settingscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.breathapplication.R
import com.example.breathapplication.settingnavigation.SettingNavItem
import com.example.breathapplication.ui.theme.Greyscale1
import com.example.breathapplication.ui.theme.Greyscale10
import com.example.breathapplication.ui.theme.Greyscale11
import com.example.breathapplication.ui.theme.Greyscale2
import com.example.breathapplication.ui.theme.Greyscale4
import com.example.breathapplication.ui.theme.Greyscale5
import com.example.breathapplication.ui.theme.Greyscale8
import com.example.breathapplication.ui.theme.Primary1
import com.example.breathapplication.ui.theme.Typography2

@Composable
fun Profile(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(color = Greyscale11)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .background(color = Greyscale10)
            )
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.CenterStart),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.arrow),
                    contentDescription = "arrow",
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .clickable{
                            navController.navigate(SettingNavItem.Setting.route)
                        }
                )
            }
            Text(
                modifier = Modifier
                    .align(Alignment.Center),
                text = "프로필 수정",
                style = Typography2.body1,
                fontWeight = FontWeight.W500,
                fontSize = 16.sp,
                color = Greyscale2
            )
        }

        Spacer(modifier = Modifier.height(60.dp))
        Box(
            modifier = Modifier
                .size(width = 320.dp, height = 80.dp)
                .align(Alignment.CenterHorizontally),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .size(width = 80.dp, height = 80.dp)
                    .clip(CircleShape)
                    .background(color = Greyscale10),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = "https://picsum.photos/80/80",
                    contentDescription = "profile picture",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(width = 80.dp, height = 80.dp)
                )
                Box(
                    modifier = Modifier
                        .size(width = 40.dp, height = 32.dp)
                        .border(width = 1.dp, color = Greyscale5, shape = RoundedCornerShape(12.dp))
                )
                Text(
                    color = Greyscale2,
                    style = Typography2.body3,
                    text = "변경"
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            text = "anonymous",
            color = Greyscale2,
            style = Typography2.subHead
        )

        Spacer(modifier = Modifier.height(40.dp))
        var nickname by remember { mutableStateOf("") }
        BasicTextField(
            value = nickname,
            onValueChange = { nickname = it},
            modifier = Modifier
                .size(width = 320.dp, height = 52.dp)
                .align(Alignment.CenterHorizontally)
                .border(
                    width = 1.dp,
                    shape = RoundedCornerShape(6.dp),
                    color = Greyscale8
                )
                .background(
                    color = Greyscale10,
                    shape = RoundedCornerShape(6.dp)
                ),
            textStyle = TextStyle(
                color = Greyscale5
            ),
            singleLine = true,
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier
                        .padding(6.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    if(nickname.isEmpty()) {
                        Text(
                            text = "닉네임 입력",
                            style = Typography2.bodyText,
                            color = Greyscale5
                        )
                    }
                    innerTextField()
                }
            }
        )

        Spacer(modifier = Modifier.height(20.dp))
        Text(
            modifier = Modifier
                .size(width = 320.dp, height = 22.dp)
                .align(Alignment.CenterHorizontally),
            style = Typography2.subTitle,
            color = Greyscale2,
            text = "직업"
        )

        Spacer(modifier = Modifier.height(10.dp))
        val selectedJobOption = remember { mutableStateOf(0) }
        val optionsJob = listOf("중/고등학생", "대학생", "직장인", "무직")
        val radioButtonColors = RadioButtonDefaults.colors(selectedColor = Primary1)

        Column(
            modifier = Modifier
                .padding(start = 20.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 50.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                    LazyRow(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        items(optionsJob.size) { index ->
                            val option = optionsJob[index]
                            Row(
                                modifier = Modifier
                                    .clickable { selectedJobOption.value = index }
                                    .padding(end = 16.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                RadioButton(
                                    selected = selectedJobOption.value == index,
                                    onClick = { selectedJobOption.value = index },
                                    colors = radioButtonColors
                                )
                                Text(
                                    style = Typography2.body2,
                                    color = Greyscale4,
                                    text = option
                                )
                            }
                        }
                    }
            }
        }

        Spacer(modifier = Modifier.height(30.dp))
        Text(
            modifier = Modifier
                .size(width = 320.dp, height = 22.dp)
                .align(Alignment.CenterHorizontally),
            style = Typography2.subTitle,
            color = Greyscale2,
            text = "연령대"
        )

        Spacer(modifier = Modifier.height(5.dp))
        val selectedAgeOption = remember { mutableStateOf(0) }
        val optionsAge = listOf("10대", "20대", "30대", "40대","50대", "60대 이상")
        val rearrangedOptionsAge = optionsAge.toMutableList().apply {
            remove("60대 이상")
            val indexOf50s = indexOf("50대")
            add(indexOf50s + 1, "60대 이상")
        }

        Column {
            rearrangedOptionsAge.chunked(3).forEach {rowOptions ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start
                ) {
                    rowOptions.forEach{ option ->
                        Row(
                            modifier = Modifier
                                .clickable {
                                    selectedAgeOption.value =
                                        rearrangedOptionsAge.indexOf(option)
                                }
                                .padding(start = 20.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = selectedAgeOption.value == rearrangedOptionsAge.indexOf(option),
                                onClick = { selectedAgeOption.value = rearrangedOptionsAge.indexOf(option)},
                                colors = radioButtonColors,
                            )
                            Text(
                                style = Typography2.body2,
                                color = Greyscale4,
                                text = option
                            )
                        }
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(32.dp))
        Button(
            onClick = {navController.navigate(SettingNavItem.Setting.route)},
            modifier = Modifier
                .size(width = 320.dp, height = 52.dp)
                .align(Alignment.CenterHorizontally),
            colors = ButtonDefaults.buttonColors(containerColor = Primary1),
            shape = RoundedCornerShape(6.dp)
        ) {
            Text(
                style = Typography2.button,
                color = Greyscale1,
                text = "수정하기"
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun ShowProfile() {
    /*
    Profile()
     */
}