package com.example.breathapplication.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.breathapplication.asleep.viewmodel.AsleepViewModel
import com.example.breathapplication.navigation.bottomnavigation.BottomNavItem
import com.example.breathapplication.gemini.chat.ChatScreen
import com.example.breathapplication.gemini.chat.ChatViewModel
import com.example.breathapplication.navigation.diary.DiaryNavItem
import com.example.breathapplication.navigation.setting.SettingNavItem
import com.example.breathapplication.navigation.sleep.SleepNavItem
import com.example.breathapplication.screen.diary.CompleteDiaryScreen
import com.example.breathapplication.screen.diary.ContinueDiaryScreen
import com.example.breathapplication.screen.diary.MyDiaryScreen
import com.example.breathapplication.screen.diary.ReadDiaryScreen
import com.example.breathapplication.screen.diary.WriteDiaryScreen
import com.example.breathapplication.screen.main.MainScreen
import com.example.breathapplication.screen.setting.Help
import com.example.breathapplication.screen.setting.Password
import com.example.breathapplication.screen.setting.Pick
import com.example.breathapplication.screen.setting.Profile
import com.example.breathapplication.screen.setting.Push
import com.example.breathapplication.screen.setting.Setting
import com.example.breathapplication.screen.sleep.SleepIngScreen
import com.example.breathapplication.screen.sleep.SleepScreen
import com.example.breathapplication.viewmodel.DiaryScreenViewModel
import com.example.breathapplication.viewmodel.MainScreenViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RootNavGraph(navController: NavHostController) {
    val diaryScreenViewModel : DiaryScreenViewModel = viewModel()
    val asleepViewModel : AsleepViewModel = hiltViewModel()
    val mainScreenViewModel : MainScreenViewModel = viewModel()

    NavHost(navController = navController, startDestination = "main") {

        /** Main */
        composable("main"){
            MainScreen(viewModel = mainScreenViewModel, diaryScreenViewModel = diaryScreenViewModel, navController = navController)
        }

        /** Chat */
        composable("chat"){
             ChatScreen(viewModel = ChatViewModel(), navController)
        }

        /** Diary */
        composable(DiaryNavItem.WriteDiaryScreen.route){
            WriteDiaryScreen(navController = navController, diaryScreenViewModel = diaryScreenViewModel)
        }
        composable(DiaryNavItem.ReadDiaryScreen.route){
            ReadDiaryScreen(navController = navController, diaryScreenViewModel = diaryScreenViewModel)
        }
        composable(DiaryNavItem.ContinueDiaryScreen.route){
            ContinueDiaryScreen(navController = navController, diaryScreenViewModel = diaryScreenViewModel)
        }
        composable(DiaryNavItem.CompleteDiaryScreen.route){
            CompleteDiaryScreen(navController = navController, diaryScreenViewModel = diaryScreenViewModel)
        }

        /** Setting */
        composable(SettingNavItem.Setting.route) {
            Setting(navController)
        }
        composable(SettingNavItem.Profile.route) {
            Profile(navController)
        }
        composable(SettingNavItem.Password.route) {
            Password(navController)
        }
        composable(SettingNavItem.Push.route) {
            Push(navController)
        }
        composable(SettingNavItem.Pick.route) {
            Pick(navController)
        }
        composable(SettingNavItem.Help.route) {
            Help(navController)
        }

        /** Sleep */
        composable(SleepNavItem.Sleep.route) {
            SleepScreen(diaryScreenViewModel = diaryScreenViewModel, navController = navController, asleepViewModel)
        }
        composable(SleepNavItem.SleepIng.route) {
            SleepIngScreen(navController = navController)
        }
    }
}