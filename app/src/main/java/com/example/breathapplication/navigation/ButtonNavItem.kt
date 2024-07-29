package com.example.breathapplication.navigation

sealed class ButtonNavItem(
    val route: String,
    val title: String,
    val icon: Int
) {
    object WriteDiaryScreen : ButtonNavItem("write_diary_screen", "수면일기작성", android.R.drawable.btn_radio)
    object ReadDiaryScreen : ButtonNavItem("read_diary_screen", "수면일기읽기", android.R.drawable.btn_radio)
    object ContinueDiaryScreen : ButtonNavItem("continue_diary_screen", "수면일기이어쓰기", android.R.drawable.btn_radio)
    object CompleteDiaryScreen : ButtonNavItem("complete_diary_screen", "수면일기확인", android.R.drawable.btn_radio)
}