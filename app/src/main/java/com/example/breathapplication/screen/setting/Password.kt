package com.example.breathapplication.screen.setting

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.breathapplication.R
import com.example.breathapplication.navigation.setting.SettingNavItem
import com.example.breathapplication.ui.theme.Greyscale1
import com.example.breathapplication.ui.theme.Greyscale10
import com.example.breathapplication.ui.theme.Greyscale11
import com.example.breathapplication.ui.theme.Greyscale2
import com.example.breathapplication.ui.theme.Greyscale5
import com.example.breathapplication.ui.theme.Greyscale8
import com.example.breathapplication.ui.theme.Primary1
import com.example.breathapplication.ui.theme.Typography2

@Composable
fun Password(navController: NavHostController) {
    Column(
        modifier = Modifier
            .background(color = Greyscale11)
            .fillMaxHeight()
            .fillMaxWidth()
            .windowInsetsPadding(WindowInsets.safeDrawing)
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
                    .size(width = 248.dp, height = 23.dp),
            )
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.CenterStart),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_arrow),
                    contentDescription = "arrow",
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .clickable{
                            navController.popBackStack()
                        }
                )
            }
            Text(
                modifier = Modifier
                    .align(Alignment.Center),
                text = "비밀번호 변경",
                style = Typography2.body1,
                fontWeight = FontWeight.W500,
                fontSize = 16.sp,
                color = Greyscale2,
            )
        }
        Spacer(modifier = Modifier.height(194.dp))
        var prevPassword by remember { mutableStateOf("") }
        BasicTextField(
            value = prevPassword,
            onValueChange = { prevPassword = it},
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .size(width = 320.dp, height = 52.dp)
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
                color = Greyscale5,
            ),
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier
                        .padding(start = 14.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    if(prevPassword.isEmpty()) {
                        Text(
                            text = "기존 비밀번호 입력",
                            style = Typography2.bodyText,
                            color = Greyscale5
                        )
                    }
                    innerTextField()
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))
        var newPassword by remember { mutableStateOf("")}
        BasicTextField(
            value = newPassword,
            onValueChange = { newPassword = it},
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .size(width = 320.dp, height = 52.dp)
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
                color = Greyscale5,
            ),
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier
                        .padding(start = 14.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    if(newPassword.isEmpty()) {
                        Text(
                            text = "새 비밀번호 입력",
                            style = Typography2.bodyText,
                            color = Greyscale5
                        )
                    }
                    innerTextField()
                }
            }
        )

        Spacer(modifier = Modifier.height(10.dp))
        var checkNewPassword by remember { mutableStateOf("") }
        BasicTextField(
            value = checkNewPassword,
            onValueChange = { checkNewPassword = it},
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .size(width = 320.dp, height = 52.dp)
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
                color = Greyscale5,
            ),
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier
                        .padding(start = 14.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    if(checkNewPassword.isEmpty()) {
                        Text(
                            text = "새 비밀번호 확인",
                            style = Typography2.bodyText,
                            color = Greyscale5
                        )
                    }
                    innerTextField()
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))
        /*수정된 비밀번호 일치여부 확인*/
        val passwordMatch = newPassword == checkNewPassword
        Box(
            modifier = Modifier
                .size(width = 320.dp, height = 14.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Text(
                modifier = Modifier
                    .align(Alignment.Center),
                text = "8~16자의 영문 대/소문자, 숫자, 특수기호 조합이 가능합니다.",
                color = Greyscale5,
                style = Typography2.body3
            )
        }

        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = {
                if(passwordMatch) {
                    navController.navigate(SettingNavItem.Setting.route)
                }
            },
            modifier = Modifier
                .size(width = 320.dp, height = 52.dp)
                .align(Alignment.CenterHorizontally),
            colors = ButtonDefaults.buttonColors(
                containerColor = if(passwordMatch) Primary1
                                else Greyscale10
            ),
            shape = RoundedCornerShape(6.dp),
            enabled = passwordMatch
        ) {
            Text(
                style = Typography2.button,
                color = Greyscale1,
                text = "변경하기"
            )
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun ShowPassword() {
    /*
    Password()
     */
}