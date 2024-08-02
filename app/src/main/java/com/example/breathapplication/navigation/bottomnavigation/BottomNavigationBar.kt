package com.example.breathapplication.navigation.bottomnavigation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.compose.runtime.*
import androidx.compose.material3.Text
import androidx.compose.ui.res.painterResource
import com.example.breathapplication.ui.theme.Greyscale10
import com.example.breathapplication.ui.theme.Greyscale5

@Composable
fun BottomNavigationBar(
    navcontroller: NavHostController
) {
    val items = listOf(
        BottomNavItem.SleepScreen,
        BottomNavItem.WriteDiary
    )

    var isSelectedTab by remember { mutableStateOf("my_diary_screen") }

    NavigationBar(
        modifier = Modifier
            .height(100.dp)
            .fillMaxWidth(),
        containerColor = Greyscale10,
    ) {
        items.forEach{item ->
            NavigationBarItem(
                selected = item.route == navcontroller.currentDestination?.route,
                onClick = {
                          navcontroller.navigate(item.route) {
                          }
                    isSelectedTab = item.route
                },
                icon = {
                       Icon(
                           painter = painterResource(id = item.icon),
                           contentDescription = item.title,
                           modifier = Modifier.size(24.dp)
                       )
                },
                label = { Text(text = item.title)})
        }
    }
}
