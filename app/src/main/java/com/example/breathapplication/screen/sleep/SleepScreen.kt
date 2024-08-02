package com.example.breathapplication.screen.sleep

import ai.asleep.asleepsdk.data.Report
import ai.asleep.asleepsdk.data.Stat
import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.PowerManager
import android.provider.Settings
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.breathapplication.R
import com.example.breathapplication.SampleApplication
import com.example.breathapplication.asleep.service.RecordService
import com.example.breathapplication.asleep.utils.changeTimeFormat
import com.example.breathapplication.asleep.utils.getCurrentTime
import com.example.breathapplication.asleep.viewmodel.AsleepViewModel
import com.example.breathapplication.component.CompleteButton
import com.example.breathapplication.ui.theme.Greyscale11
import com.example.breathapplication.ui.theme.Greyscale2
import com.example.breathapplication.ui.theme.Greyscale4
import com.example.breathapplication.ui.theme.Greyscale5
import com.example.breathapplication.ui.theme.Primary2
import com.example.breathapplication.ui.theme.Typography2
import com.example.breathapplication.viewmodel.DiaryScreenViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SleepScreen(diaryScreenViewModel: DiaryScreenViewModel, navController: NavHostController, asleepViewModel: AsleepViewModel) {
    val context = LocalContext.current
    val userId by asleepViewModel.userId.observeAsState()
    val report by asleepViewModel.report.observeAsState()

    val permissionsLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
        onResult = { permissions ->
            val granted = permissions.entries.all { it.value }
            if (granted) {
                asleepViewModel.clearSleepTrackingState()
                with(SampleApplication.sharedPref.edit()) {
                    putString("start_tracking_time", getCurrentTime())
                    apply()
                }
                startTrackingService(context)
                // Move to tracking screen
                navController.navigate("sleep_ing")
            } else {
                Toast.makeText(context, "Permissions denied", Toast.LENGTH_SHORT).show()
            }
        }
    )

    Column(
        Modifier
            .background(color = Greyscale11)
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.safeDrawing)
            .padding(start = 20.dp, end = 20.dp)
    ) {
        Spacer(modifier = Modifier.height(91.dp))
        Text(
            diaryScreenViewModel.subTitleDate.value,
            style = Typography2.h1,
            color = Greyscale2,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(37.dp))
        val text = buildAnnotatedString {
            append("측정이 시작하면, 홍길동님의\n")
            withStyle(style = SpanStyle(color = Primary2)) {
                append("수면 중 호흡")
            }
            append("을 분석할 거예요.")
        }
        Text(
            text,
            style = Typography2.subHead,
            color = Greyscale4,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(38.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ){
            Icon(
                painter = painterResource(id = R.drawable.ic_warning),
                contentDescription = "warning",
                tint = Color.Unspecified,
                modifier = Modifier.padding(top = 5.dp)
            )
            Spacer(modifier = Modifier.width(5.dp))

            Text(
                "측정 도중 전원이 꺼지지 않도록\n주의해주세요.",
                style = Typography2.subHead,
                color = Greyscale4,
                textAlign = TextAlign.Center,
            )
        }
        Spacer(modifier = Modifier.height(46.dp))

        Icon(
            painter = painterResource(R.drawable.ic_three_circle),
            contentDescription = "circle",
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            tint = Color.Unspecified,
        )
        Spacer(modifier = Modifier.height(76.dp))

        Text(
            "잠에 들 준비가 되셨나요?",
            style = Typography2.subTitle,
            color = Greyscale5,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        CompleteButton(text = "수면 측정 시작하기", onClick = {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
                asleepViewModel.clearSleepTrackingState()

                with(SampleApplication.sharedPref.edit()) {
                    putString("start_tracking_time", getCurrentTime())
                    apply()
                }
                startTrackingService(context)
                // Move to tracking screen
                navController.navigate("sleep_ing")
            } else {
                val permissions = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    arrayOf(Manifest.permission.RECORD_AUDIO, Manifest.permission.POST_NOTIFICATIONS)
                } else {
                    arrayOf(Manifest.permission.RECORD_AUDIO)
                }
                permissionsLauncher.launch(permissions)
            }
        })
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

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showSystemUi = true)
@Composable
fun SleepScreenPreview() {
    val navController = rememberNavController()
    SleepScreen(diaryScreenViewModel = DiaryScreenViewModel(), navController = navController, asleepViewModel = hiltViewModel())
}