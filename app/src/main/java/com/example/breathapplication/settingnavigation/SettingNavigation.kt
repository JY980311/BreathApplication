package com.example.breathapplication.settingnavigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController

@Composable
fun SettingNavGraph() {
    val navController = rememberNavController()
    NavGraph(navController = navController)
}