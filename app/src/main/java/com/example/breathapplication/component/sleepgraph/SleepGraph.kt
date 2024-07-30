package com.example.breathapplication.component.sleepgraph

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.breathapplication.network.test.TestViewModel
import com.example.breathapplication.ui.theme.Greyscale10
import com.example.breathapplication.ui.theme.Greyscale5
import com.example.breathapplication.ui.theme.Typography2

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun SleepGraph(
    viewModel: TestViewModel
) {
    val sleepStages = viewModel.asleepData.collectAsStateWithLifecycle() // 데이터 구독하는 부분

    val stages = sleepStages.value.result.session.sleep_stages.mapIndexed { index, stage ->
        val stageType = when (stage) {
            -1 -> SleepStageType.ERROR
            0 -> SleepStageType.AWAKE
            1 -> SleepStageType.LIGHT
            2 -> SleepStageType.DEEP
            3 -> SleepStageType.REM
            else -> SleepStageType.ERROR
        }
        SleepStageData(startTime = index * 0.0083f, duration = 0.0083f, stage = stageType) // 30초 단위로 설정
    }

    Log.d("sleepStages", sleepStages.value.result.session.sleep_stages.toString())

    Box(
        modifier = Modifier
            .height(186.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(Greyscale10)
    ) {
        Row {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(start = 18.dp, bottom = 22.dp, top = 22.dp),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = "비수면",
                    style = Typography2.body4,
                    color = Greyscale5
                )
                Text(
                    text = "얕은잠",
                    style = Typography2.body4,
                    color = Greyscale5
                )
                Text(
                    text = "깊은잠",
                    style = Typography2.body4,
                    color = Greyscale5
                )
                Text(
                    text = "렘수면",
                    style = Typography2.body4,
                    color = Greyscale5
                )
            }
            val startTime = sleepStages.value.result.session.start_time

            // 안전하게 문자열을 분할하고 시간 부분을 추출
            val hourPart: Int? = try {
                val dateTimeParts = startTime.split("T")
                if (dateTimeParts.size > 1) {
                    val timeParts = dateTimeParts[1].split(":")
                    if (timeParts.isNotEmpty()) {
                        timeParts[0].toInt()
                    } else {
                        null
                    }
                } else {
                    null
                }
            } catch (e: Exception) {
                Log.e("sleepStages", "Error parsing start_time", e)
                null
            }

            // hourPart를 SleepChartItem에 전달
            if (hourPart != null) {
                SleepChartItem(
                    stages = stages,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(vertical = 18.dp)
                        .padding(end = 18.dp, start = 16.dp),
                    time = hourPart
                )
            } else {
                // hourPart가 null인 경우 적절한 조치
                SleepChartItem(
                    stages = stages,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(vertical = 18.dp)
                        .padding(end = 18.dp, start = 16.dp),
                    time = 0 // 기본값으로 설정하거나 다른 처리를 할 수 있습니다.
                )
            }
        }
    }
}

fun main() {
    val timeString = "2024-07-22T17:39:35+00:00"
    val timePart = timeString.split("T")[1].split(":")[0].toInt()
    println(timePart) // 출력: 17
}


@Preview(showBackground = true)
@Composable
fun SleepGraphPreview() {
    SleepGraph(viewModel = TestViewModel())
}