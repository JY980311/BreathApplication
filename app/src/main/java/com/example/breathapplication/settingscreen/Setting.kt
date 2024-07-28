package com.example.breathapplication.settingscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.breathapplication.ui.theme.Greyscale10
import com.example.breathapplication.ui.theme.Greyscale11
import androidx.compose.material3.Text
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.breathapplication.R
import com.example.breathapplication.settingnavigation.SettingNavItem
import com.example.breathapplication.ui.theme.Greyscale2

@Composable
fun Setting(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(color = Greyscale11)
    ) {
        SettingHeader()
        SettingButton(
            iconId = R.drawable.icon_setting,
            text = "프로필 수정",
            onClick = {navController.navigate(SettingNavItem.Profile.route)}
        )
        SettingButton(
            iconId = R.drawable.icon_setting_lock,
            text = "비밀번호 변경",
            onClick = {navController.navigate(SettingNavItem.Password.route)}
        )
        SettingButton(
            iconId = R.drawable.icon_setting_bell,
            text = "푸시 알림 설정",
            onClick = {navController.navigate(SettingNavItem.Push.route)}
        )
        SettingButton(
            iconId = R.drawable.icon_setting_jjim,
            text = "찜 목록",
            onClick = {navController.navigate(SettingNavItem.Pick.route)}
        )
        SettingButton(
            iconId = R.drawable.icon_setting_ask,
            text = "문의하기",
            onClick = {navController.navigate(SettingNavItem.Help.route)}
        )
    }
}

@Composable
fun SettingHeader() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(color = Greyscale10)
    ) {
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
            )
        }
        Text(
            modifier = Modifier
                .align(Alignment.Center),
            text = "환경설정",
            fontSize = 16.sp,
            color = Greyscale2
        )
    }

    Spacer(modifier = Modifier.height(216.dp))
}

@Composable
fun SettingButton(
    iconId: Int,
    text: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(58.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Greyscale10),
        shape = RoundedCornerShape(0.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = iconId),
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(30.dp))
            Text(
                text = text,
                fontWeight = FontWeight.W500,
                fontSize = 16.sp,
                color = Greyscale2,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .size(width = 205.dp, height = 23.dp)
            )
            Spacer(modifier = Modifier.width(50.dp))
            Image(
                painter = painterResource(id = R.drawable.icon_arrow_right),
                contentDescription = "icon_arrow_right"
            )
        }
    }
    Spacer(modifier = Modifier.height(1.dp))
}

@Preview(showSystemUi = true)
@Composable
fun ShowSetting() {
    /*
    Setting()
     */
}