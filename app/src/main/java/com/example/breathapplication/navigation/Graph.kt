package com.example.breathapplication.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.breathapplication.screen.ReadDiaryScreen
import com.example.breathapplication.screen.WriteDiaryScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavigationGraph(navController: NavHostController){
    NavHost(navController = navController, startDestination = ButtonNavItem.WriteDiaryScreen.route){
        composable(ButtonNavItem.WriteDiaryScreen.route){
            WriteDiaryScreen(navController = navController)
        }
        composable(ButtonNavItem.ReadDiaryScreen.route){
            ReadDiaryScreen(navController = navController)
        }
    }
}