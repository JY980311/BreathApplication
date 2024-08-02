package com.example.breathapplication.asleep.screen

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.breathapplication.SampleApplication
import com.example.breathapplication.asleep.service.RecordService
import com.example.breathapplication.asleep.viewmodel.AsleepViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TrackingActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
        }
    }
}

@Composable
fun TrackingScreen(viewModel: AsleepViewModel = hiltViewModel(), navController: NavHostController) {
    val context = LocalContext.current
    val userId by viewModel.userId.observeAsState()
    val sequence by viewModel.sequence.observeAsState()

    val startTime = SampleApplication.sharedPref.getString("start_tracking_time", null)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "User ID: $userId", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = getSequenceText(sequence), style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            stopSleepTracking(context)
            navController.navigate("home")
        }) {
            Text(text = "Stop Tracking")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Start Time: $startTime", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Tracking guidance message goes here", style = MaterialTheme.typography.bodyLarge)
    }
}

@Composable
fun getSequenceText(sequence: Int?): String {
    val context = LocalContext.current
    return buildString {
        append("Uploaded Sequence :\\t")
        append(if (sequence == null) {
            "- (0.0 min.)"
        } else {
            "$sequence ${String.format("(%1\$.1f min.)", (sequence + 1) * 0.5)}"
        })
    }
}

private fun stopSleepTracking(context: Context) {
    val intent = Intent(context, RecordService::class.java)
    intent.action = RecordService.ACTION_STOP_SERVICE
    context.startService(intent)
}