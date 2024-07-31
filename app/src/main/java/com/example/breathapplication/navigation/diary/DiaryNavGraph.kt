package com.example.breathapplication.navigation.diary

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.breathapplication.screen.diary.CompleteDiaryScreen
import com.example.breathapplication.screen.diary.ContinueDiaryScreen
import com.example.breathapplication.screen.diary.ReadDiaryScreen
import com.example.breathapplication.screen.diary.WriteDiaryScreen
import com.example.breathapplication.viewmodel.DiaryScreenViewModel

@SuppressLint("UnrememberedGetBackStackEntry")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DiaryNavGraph(navController: NavHostController){
    val diaryScreenViewModel : DiaryScreenViewModel = viewModel()

    NavHost(navController = navController, startDestination = DiaryNavItem.WriteDiaryScreen.route){
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
    }
}