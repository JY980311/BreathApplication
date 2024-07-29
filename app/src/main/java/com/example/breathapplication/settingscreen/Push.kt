package com.example.breathapplication.settingscreen

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.breathapplication.R
import com.example.breathapplication.settingnavigation.SettingNavItem
import com.example.breathapplication.ui.theme.Greyscale10
import com.example.breathapplication.ui.theme.Greyscale11
import com.example.breathapplication.ui.theme.Greyscale2
import com.example.breathapplication.ui.theme.Greyscale5
import com.example.breathapplication.ui.theme.Greyscale6
import com.example.breathapplication.ui.theme.Greyscale7
import com.example.breathapplication.ui.theme.Greyscale8
import com.example.breathapplication.ui.theme.Primary1
import com.example.breathapplication.ui.theme.Typography2

@Composable
fun Push(navController: NavHostController) {
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
                    .padding(start = 0.dp, top = 8.dp, end = 0.dp, bottom = 8.dp)
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
                style = Typography2.body1,
                color = Greyscale2,
                text = "푸시 알림 설정"
            )
        }

        Spacer(modifier = Modifier.height(156.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(58.dp)
                .background(color = Greyscale10)
                .border(
                    border = BorderStroke(
                        width = 1.dp,
                        color = Greyscale8
                    )
                ),
            contentAlignment = Alignment.CenterStart
        ) {
            Row(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Image(
                    painter = painterResource(id = R.drawable.icon_setting_bell),
                    contentDescription = "icon_setting_bell"
                )
                Text(
                    modifier = Modifier
                        .width(205.dp)
                        .height(23.dp)
                        .padding(top = 2.5.dp)
                        .offset(x = 40.dp),
                    text = "푸시 알림",
                    style = Typography2.body1,
                    color = Greyscale2
                )
                Spacer(modifier = Modifier.width(50.dp))
                val checked = remember { mutableStateOf(true) }
                Switch(
                    checked = checked.value,
                    onCheckedChange = {isChecked ->
                        checked.value = isChecked
                    },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = Greyscale2,
                        checkedTrackColor = Primary1,
                        checkedBorderColor = Greyscale8,
                        uncheckedThumbColor = Greyscale5,
                        uncheckedTrackColor = Greyscale7,
                        uncheckedBorderColor = Greyscale6
                    ),
                    modifier = Modifier
                        .width(45.dp)
                        .height(30.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(58.dp)
                .background(color = Greyscale10)
                .border(
                    border = BorderStroke(
                        width = 1.dp,
                        color = Greyscale8
                    )
                ),
            contentAlignment = Alignment.CenterStart
        ) {
            Row(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Image(
                    painter = painterResource(id = R.drawable.icon_moon),
                    contentDescription = "icon_moon"
                )
                Text(
                    modifier = Modifier
                        .width(205.dp)
                        .height(23.dp)
                        .padding(2.5.dp)
                        .offset(x = 40.dp),
                    text = "잘자요 알림",
                    style = Typography2.body1,
                    color = Greyscale2
                )

                Spacer(modifier = Modifier.width(50.dp))
                val checked = remember { mutableStateOf(true) }
                Switch(
                    checked = checked.value,
                    onCheckedChange = {isChecked ->
                        checked.value = isChecked
                    },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = Greyscale2,
                        checkedTrackColor = Primary1,
                        checkedBorderColor = Greyscale8,
                        uncheckedThumbColor = Greyscale5,
                        uncheckedTrackColor = Greyscale7,
                        uncheckedBorderColor = Greyscale6
                    ),
                    modifier = Modifier
                        .width(45.dp)
                        .height(30.dp)
                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(58.dp)
                .background(color = Greyscale10)
                .border(
                    border = BorderStroke(
                        width = 1.dp,
                        color = Greyscale8
                    )
                ),
            contentAlignment = Alignment.CenterStart
        ) {
            Row(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Image(
                    painter = painterResource(id = R.drawable.icon_setting_warning),
                    contentDescription = "icon_setting_warning"
                )
                Text(
                    modifier = Modifier
                        .width(205.dp)
                        .height(23.dp)
                        .padding(2.5.dp)
                        .offset(x = 40.dp),
                    text = "수면 유도 알림",
                    style = Typography2.body1,
                    color = Greyscale2
                )
                Spacer(modifier = Modifier.width(50.dp))
                val checked = remember { mutableStateOf(true) }
                Switch(
                    checked = checked.value,
                    onCheckedChange = {isChecked ->
                        checked.value = isChecked
                    },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = Greyscale2,
                        checkedTrackColor = Primary1,
                        checkedBorderColor = Greyscale8,
                        uncheckedThumbColor = Greyscale5,
                        uncheckedTrackColor = Greyscale7,
                        uncheckedBorderColor = Greyscale6
                    ),
                    modifier = Modifier
                        .width(45.dp)
                        .height(30.dp)
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun ShowPush() {
    /*
    Push()
     */
}