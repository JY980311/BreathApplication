package com.example.breathapplication.navigation.setting

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.breathapplication.screen.setting.Help
import com.example.breathapplication.screen.setting.Password
import com.example.breathapplication.screen.setting.Pick
import com.example.breathapplication.screen.setting.Profile
import com.example.breathapplication.screen.setting.Push
import com.example.breathapplication.screen.setting.Setting

@Composable
fun SettingNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "setting") {
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
    }
}