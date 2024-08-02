package com.example.breathapplication.navigation.sleep

sealed class SleepNavItem(
    val route: String
) {
    object Sleep: SleepNavItem("sleep")

    object SleepIng: SleepNavItem("sleep_ing")
}