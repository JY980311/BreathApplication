package com.example.breathapplication.navigation.bottomnavigation

import androidx.compose.foundation.Image
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.example.breathapplication.R
import com.example.breathapplication.navigation.diary.DiaryNavItem
import com.example.breathapplication.navigation.sleep.SleepNavItem

sealed class BottomNavItem(
    val route: String,
    val title: String,
    val icon: @Composable (Boolean) -> Unit
) {
    object SleepScreen : BottomNavItem(SleepNavItem.Sleep.route, "수면 측정", { isSelect ->
        if (isSelect) {
            Image(imageVector = ImageVector.vectorResource(R.drawable.ic_sleep_clicked), contentDescription = "클릭한 sleep 아이콘")
        } else {
            Image(imageVector = ImageVector.vectorResource(R.drawable.ic_sleep_disclicked), contentDescription = "클릭 하지 않는 sleep 아이콘")
        }
    })

    object MainScreen : BottomNavItem("main", "나의 수면", { isSelect ->
        if(isSelect) {
            Image(imageVector = ImageVector.vectorResource(R.drawable.ic_my_clicked), contentDescription = "클릭한 my 아이콘")
        } else {
            Image(imageVector = ImageVector.vectorResource(R.drawable.ic_my_disclicked), contentDescription = "클릭하지 않은 my 아이콘")
        }
    })
    object WriteDiary : BottomNavItem(DiaryNavItem.WriteDiaryScreen.route, "수면 일기", { isSelect ->
        if(isSelect) {
            Image(imageVector = ImageVector.vectorResource(R.drawable.ic_diary_clicked), contentDescription = "클릭한 diary 아이콘")
        } else {
            Image(imageVector = ImageVector.vectorResource(R.drawable.ic_diary_disclicked), contentDescription = "클릭하지 않은 diary 아이콘")
        }
    })
}

/**
 *  R.drawable.ic_sleeping_check
 *  R.drawable.ic_my
 *  R.drawable.ic_diary
 * */