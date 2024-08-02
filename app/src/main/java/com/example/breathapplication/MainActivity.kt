package com.example.breathapplication

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.breathapplication.asleep.screen.HomeContent
import com.example.breathapplication.asleep.screen.TrackingScreen
import com.example.breathapplication.asleep.service.RecordService
import com.example.breathapplication.asleep.viewmodel.AsleepViewModel
import com.example.breathapplication.navigation.RootNavGraph
import com.example.breathapplication.ui.theme.BreathApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val asleepViewModel: AsleepViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        asleepViewModel.getSingleReport()

        enableEdgeToEdge() /** 상단바 투명 설정(안보이게 하기) */

        setContent {
            val navController = rememberNavController()
            val context = LocalContext.current
            val isRunningService = remember { mutableStateOf(false) }

            if (savedInstanceState == null) {
                LaunchedEffect(Unit) {
                    isRunningService.value = RecordService.isRecordServiceRunning(context)
                    if (!isRunningService.value) {
                        initAsleepConfig()
                        navController.navigate("write_diary_screen") {
                            popUpTo(navController.graph.startDestinationId) {
                                inclusive = true
                            }
                        }
                    } else {
                        navController.navigate("sleep") {
                            popUpTo(navController.graph.startDestinationId) {
                                inclusive = true
                            }
                        }
                    }
                }
            }

            BreathApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RootNavGraph(navController = navController)
                }
            }
        }
    }

    private fun initAsleepConfig() {
        val storedUserId = SampleApplication.sharedPref.getString("user_id", null)
        asleepViewModel.initAsleepConfig(storedUserId)
    }

}
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TestApp(){
    val navController = rememberNavController()
    RootNavGraph(navController = navController)
}

@Composable
fun MyAppNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") { HomeContent(navController = navController) }
        composable("tracking") { TrackingScreen(navController = navController) }
    }
}
