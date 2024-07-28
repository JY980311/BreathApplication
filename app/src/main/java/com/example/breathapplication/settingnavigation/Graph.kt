package com.example.breathapplication.settingnavigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.breathapplication.settingscreen.Help
import com.example.breathapplication.settingscreen.Password
import com.example.breathapplication.settingscreen.Pick
import com.example.breathapplication.settingscreen.Profile
import com.example.breathapplication.settingscreen.Push
import com.example.breathapplication.settingscreen.Setting

@Composable
fun NavGraph(navController: NavHostController) {
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