package com.example.breathapplication.navigation

sealed class ButtonNavItem(
    val route: String,
    val title: String,
    val icon: Int
) {
    object WriteDiaryScreen : ButtonNavItem("write_diary_screen", "수면일기작성화면", android.R.drawable.btn_radio)
    object ReadDiaryScreen : ButtonNavItem("read_diary_screen", "수면일기읽기화면", android.R.drawable.btn_radio)
}