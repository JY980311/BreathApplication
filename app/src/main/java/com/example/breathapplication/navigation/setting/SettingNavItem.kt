package com.example.breathapplication.navigation.setting

sealed class SettingNavItem(
    val route: String
) {
    object Setting: SettingNavItem("setting")
    object Profile: SettingNavItem("profile")
    object Password: SettingNavItem("password")
    object Push: SettingNavItem("push")
    object Pick: SettingNavItem("pick")
    object Help: SettingNavItem("help")

}