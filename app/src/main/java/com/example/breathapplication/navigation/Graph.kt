package com.example.breathapplication.navigation

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.breathapplication.screen.CompleteDiaryScreen
import com.example.breathapplication.screen.ContinueDiaryScreen
import com.example.breathapplication.screen.ReadDiaryScreen
import com.example.breathapplication.screen.WriteDiaryScreen
import com.example.breathapplication.viewmodel.DiaryScreenViewModel

@SuppressLint("UnrememberedGetBackStackEntry")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavigationGraph(navController: NavHostController){
    val diaryScreenViewModel : DiaryScreenViewModel = viewModel()

    NavHost(navController = navController, startDestination = ButtonNavItem.WriteDiaryScreen.route){
        composable(ButtonNavItem.WriteDiaryScreen.route){
            WriteDiaryScreen(navController = navController, diaryScreenViewModel = diaryScreenViewModel)
        }
        composable(ButtonNavItem.ReadDiaryScreen.route){
            ReadDiaryScreen(navController = navController, diaryScreenViewModel = diaryScreenViewModel)
        }
        composable(ButtonNavItem.ContinueDiaryScreen.route){
            ContinueDiaryScreen(navController = navController, diaryScreenViewModel = diaryScreenViewModel)
        }
        composable(ButtonNavItem.CompleteDiaryScreen.route){
            CompleteDiaryScreen(navController = navController, diaryScreenViewModel = diaryScreenViewModel)
        }
    }
}