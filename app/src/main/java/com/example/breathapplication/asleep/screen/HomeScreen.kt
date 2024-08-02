package com.example.breathapplication.asleep.screen

import ai.asleep.asleepsdk.data.Report
import ai.asleep.asleepsdk.data.Stat
import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.PowerManager
import android.provider.Settings
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.breathapplication.SampleApplication
import com.example.breathapplication.asleep.service.RecordService
import com.example.breathapplication.asleep.utils.changeTimeFormat
import com.example.breathapplication.asleep.utils.getCurrentTime
import com.example.breathapplication.asleep.viewmodel.AsleepViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeScreen : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

        }
    }
}

@Composable
fun HomeContent(viewModel: AsleepViewModel = hiltViewModel(), navController: NavHostController) {
    val context = LocalContext.current
    val userId by viewModel.userId.observeAsState()
    val report by viewModel.report.observeAsState()

    val permissionsLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
        onResult = { permissions ->
            val granted = permissions.entries.all { it.value }
            if (granted) {
                viewModel.clearSleepTrackingState()
                with(SampleApplication.sharedPref.edit()) {
                    putString("start_tracking_time", getCurrentTime())
                    apply()
                }
                startTrackingService(context)
                // Move to tracking screen
                navController.navigate("tracking")
            } else {
                Toast.makeText(context, "Permissions denied", Toast.LENGTH_SHORT).show()
            }
        }
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "User ID: $userId", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))

        report?.let {
            displayReportText(it)
        } ?: run {
            Text(text = "No report available")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
                viewModel.clearSleepTrackingState()

                with(SampleApplication.sharedPref.edit()) {
                    putString("start_tracking_time", getCurrentTime())
                    apply()
                }
                startTrackingService(context)
                // Move to tracking screen
                navController.navigate("tracking")
            } else {
                val permissions = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    arrayOf(Manifest.permission.RECORD_AUDIO, Manifest.permission.POST_NOTIFICATIONS)
                } else {
                    arrayOf(Manifest.permission.RECORD_AUDIO)
                }
                permissionsLauncher.launch(permissions)
            }
        }) {
            Text(text = "Start Tracking")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = { viewModel.getSingleReport() }) {
            Text(text = "Get Report Again")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = { ignoreBatteryOptimizations(context) }) {
            Text(text = "Ignore Battery Opt")
        }
    }
}

@Composable
fun displayReportText(report: Report) {
    val reportText = getReportText(report)
    val statText = report.stat?.let { getStatText(it) } ?: "Stat is null"

    Column {
        Text(text = "Session ID: ${report.session?.id}", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = reportText, style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = statText, style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = "Sleep Stages: ${report.session?.sleepStages}", style = MaterialTheme.typography.bodyLarge)
        Text(text = "Breath Stages: ${report.session?.breathStages}", style = MaterialTheme.typography.bodyLarge)
        Text(text = "Snoring Stages: ${report.session?.snoringStages}", style = MaterialTheme.typography.bodyLarge)
    }
}

private fun getReportText(report: Report): String {
    return "Created Timezone: ${report.session?.createdTimezone}\n" +
            "Time Range: ${changeTimeFormat(report.session?.startTime)} ~ ${changeTimeFormat(report.session?.endTime)}\n" +
            "Unexpected Timezone: ${report.session?.unexpectedEndTime}\n" +
            "Session State: ${report.session?.state}\n" +
            "Missing Data Ratio: ${report.missingDataRatio}\n" +
            "Peculiarities: ${report.peculiarities}"
}

private fun getStatText(stat: Stat): String {
    return "SleepLatency: ${stat.sleepLatency}\n" +
            "WakeLatency: ${stat.wakeupLatency}\n" +
            "SleepTime: ${stat.sleepTime}\n" +
            "WakeTime: ${stat.wakeTime}\n" +
            "TimeInWake: ${stat.timeInWake}\n" +
            "TimeInSleep: ${stat.timeInSleep}\n" +
            "TimeInBed: ${stat.timeInBed}\n" +
            "TimeInSleepPeriod: ${stat.timeInSleepPeriod}\n" +
            "TimeInRem: ${stat.timeInRem}\n" +
            "TimeInLight: ${stat.timeInLight}\n" +
            "TimeInDeep: ${stat.timeInDeep}\n" +
            "TimeInStableBreath: ${stat.timeInStableBreath}\n" +
            "TimeInUnstableBreath: ${stat.timeInUnstableBreath}\n" +
            "SleepEfficiency: ${stat.sleepEfficiency}\n" +
            "WakeRatio: ${stat.wakeRatio}\n" +
            "SleepRatio: ${stat.sleepRatio}\n" +
            "RemRatio: ${stat.remRatio}\n" +
            "LightRatio: ${stat.lightRatio}\n" +
            "DeepRatio: ${stat.deepRatio}\n" +
            "StableBreathRatio: ${stat.stableBreathRatio}\n" +
            "UnstableBreathRatio: ${stat.unstableBreathRatio}\n" +
            "BreathingPattern: ${stat.breathingPattern}\n" +
            "BreathingIndex: ${stat.breathingIndex}\n" +
            "SleepCycle: ${stat.sleepCycle}\n" +
            "SleepCycleCount: ${stat.sleepCycleCount}\n" +
            "WasoCount: ${stat.wasoCount}\n" +
            "LongestWaso: ${stat.longestWaso}\n"
}

private fun ignoreBatteryOptimizations(context: Context) {
    val pm = context.getSystemService(Context.POWER_SERVICE) as PowerManager
    if (pm.isIgnoringBatteryOptimizations(context.packageName)) {
        Toast.makeText(context, "Battery optimization is already being ignored.", Toast.LENGTH_SHORT).show()
    } else {
        val intent = Intent()
        intent.action = Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS
        intent.data = Uri.parse("package:${context.packageName}")
        context.startActivity(intent)
    }
}

private fun startTrackingService(context: Context) {
    val intent = Intent(context, RecordService::class.java)
    intent.action = RecordService.ACTION_START_OR_RESUME_SERVICE
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        context.startForegroundService(intent)
    } else {
        context.startService(intent)
    }
}