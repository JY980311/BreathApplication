package com.example.breathapplication.navigation.diary

sealed class DiaryNavItem(
    val route: String,
    val icon: Int
) {
    object WriteDiaryScreen : DiaryNavItem("write_diary_screen",  android.R.drawable.btn_radio)
    object ReadDiaryScreen : DiaryNavItem("read_diary_screen",  android.R.drawable.btn_radio)
    object ContinueDiaryScreen : DiaryNavItem("continue_diary_screen",  android.R.drawable.btn_radio)
    object CompleteDiaryScreen : DiaryNavItem("complete_diary_screen",  android.R.drawable.btn_radio)
}