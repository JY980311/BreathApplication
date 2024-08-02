package com.example.breathapplication.component

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.breathapplication.R
import com.example.breathapplication.navigation.setting.SettingNavItem
import com.example.breathapplication.ui.theme.Greyscale10
import com.example.breathapplication.ui.theme.Greyscale2
import com.example.breathapplication.ui.theme.Typography2
import com.example.breathapplication.viewmodel.DiaryScreenViewModel

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TobBar(title: String, leftIcon : Int? = null, rightIcon : Int? = null, navController: NavHostController? = null, diaryScreenViewModel: DiaryScreenViewModel? = null){
    TopAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        navigationIcon = {
            if(leftIcon != null) {
                Row(
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .clickable {
                            if (leftIcon == R.drawable.ic_calendar && diaryScreenViewModel != null) {
                                diaryScreenViewModel.isCalendarClicked.value = !diaryScreenViewModel.isCalendarClicked.value
                            }
                            if(leftIcon == R.drawable.ic_arrow && navController != null){
                                navController.popBackStack()
                            }
                        },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                ) {
                    Icon(
                        modifier = Modifier.fillMaxHeight(),
                        painter = painterResource(id = leftIcon),
                        contentDescription = "leftIcon",
                        tint = Greyscale2,
                    )
                }
            }
        },
        title = {
            Row(
                modifier = Modifier
                    .fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {

                Text(
                    text = title,
                    style = Typography2.body1,
                    color = Greyscale2,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(1f)
                )
            }
        },
        actions = {
            if(rightIcon != null) {
                Row(
                    modifier = Modifier
                        .padding(end = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End,
                ) {
                    Icon(
                        modifier = Modifier
                            .fillMaxHeight()
                            .then(
                                if (navController != null) {
                                    Modifier.clickable {
                                        navController.navigate(SettingNavItem.Setting.route)
                                    }
                                } else {
                                    Modifier
                                }
                            ),
                        painter = painterResource(id = rightIcon),
                        contentDescription = "rightIcon",
                        tint = Greyscale2
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Greyscale10
        )
    )
}