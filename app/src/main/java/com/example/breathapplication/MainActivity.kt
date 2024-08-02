package com.example.breathapplication

import BottomNavigationBar
import ai.asleep.asleepsdk.Asleep.Companion.initAsleepConfig
import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
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
import javax.inject.Inject

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
            MainViewWrapper(asleepViewModel = asleepViewModel)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainViewWrapper(asleepViewModel: AsleepViewModel) {
    val navController = rememberNavController()
    val context = LocalContext.current
    val isRunningService = remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        isRunningService.value = RecordService.isRecordServiceRunning(context)
        if (!isRunningService.value) {
            initAsleepConfig(asleepViewModel)
            navController.navigate("main") {
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

    BreathApplicationTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            MainView(navController = navController)
        }
    }
}

private fun initAsleepConfig(asleepViewModel: AsleepViewModel) {
    val storedUserId = SampleApplication.sharedPref.getString("user_id", null)
    asleepViewModel.initAsleepConfig(storedUserId)
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


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainView(
    navController: NavHostController
) {
    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            RootNavGraph(navController = navController)
        }
    }
}
