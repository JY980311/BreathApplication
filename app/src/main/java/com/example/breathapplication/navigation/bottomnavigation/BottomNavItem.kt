package com.example.breathapplication.navigation.bottomnavigation

import com.example.breathapplication.R

sealed class BottomNavItem(
    val route: String,
    val title: String,
    val icon: Int
) {
    object SleepScreen: BottomNavItem("sleep_screen", "수면 측정", R.drawable.ic_sleeping_check)
    object MainScreen: BottomNavItem("my_sleep", "나의 수면", R.drawable.ic_my)
    object WriteDiary: BottomNavItem("wirte_diary_screen", "수면 일기", R.drawable.ic_diary)
}