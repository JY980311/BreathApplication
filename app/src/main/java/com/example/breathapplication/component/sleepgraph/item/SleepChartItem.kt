package com.example.breathapplication.component.sleepgraph.item

import android.graphics.Typeface
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import com.example.breathapplication.component.sleepgraph.data.SleepStageData
import com.example.breathapplication.ui.theme.Greyscale6
import com.example.breathapplication.ui.theme.Primary1
import com.example.breathapplication.ui.theme.Secondary1
enum class SleepStageType {
    AWAKE, LIGHT, DEEP, REM, ERROR
}


@Composable
fun SleepChartItem(
    modifier: Modifier = Modifier,
    stages: List<SleepStageData>,
    time: Int
) {
    val colors = mapOf(
        SleepStageType.AWAKE to Color(0xFF8BAAF8),
        SleepStageType.REM to Secondary1,
        SleepStageType.LIGHT to Primary1,
        SleepStageType.DEEP to Color(0xFF6746E9),
        SleepStageType.ERROR to Color.Red
    )

    val stagePositions = mapOf(
        SleepStageType.AWAKE to 0.1f,
        SleepStageType.REM to 0.3f,
        SleepStageType.LIGHT to 0.5f,
        SleepStageType.DEEP to 0.7f,
    )

    Canvas(modifier = modifier) {
        val width = size.width
        val height = size.height
        val totalDuration = stages.size * 0.0083f // 총 시간을 시간 단위로 변환
        val unitWidth = width / totalDuration

        // 3시간 간격으로 세로 줄 그리기
        val threeHourInterval = 3.0f // 3시간
        val intervalWidth = threeHourInterval / totalDuration * width

        stages.forEach { stage ->
            val color = colors[stage.stage] ?: Color.Gray
            val left = stage.startTime * unitWidth
            val right = left + stage.duration * unitWidth
            val top = when (stage.stage) {
                SleepStageType.AWAKE -> 0.1f * height
                SleepStageType.REM -> 0.3f * height
                SleepStageType.LIGHT -> 0.5f * height
                SleepStageType.DEEP -> 0.7f * height
                SleepStageType.ERROR -> 0.9f * height
            }

            val bottom = top + 0.2f * height

            drawRect(
                color = color,
                topLeft = Offset(left, top),
                size = Size(right - left, bottom - top),
                style = Fill
            )
        }

        /*drawLine(
            color = Color(0xFF26282D),
            start = Offset(height - 325, 31f),
            end = Offset(width, 31f),
            strokeWidth = 1f
        )

        drawLine(
            color = Color(0xFF26282D),
            start = Offset(height - 325, 97f),
            end = Offset(width, 97f),
            strokeWidth = 1f
        )

        drawLine(
            color = Color(0xFF26282D),
            start = Offset(height - 325, 163f),
            end = Offset(width, 163f),
            strokeWidth = 1f
        )

        drawLine(
            color = Color(0xFF26282D),
            start = Offset(height - 325, 228f),
            end = Offset(width, 228f),
            strokeWidth = 1f
        )

        drawLine(
            color = Color(0xFF26282D),
            start = Offset(height - 325, 293f),
            end = Offset(width, 293f),
            strokeWidth = 1f
        )*/

        stagePositions.values.forEach { topPosition ->
            val yPos = topPosition * height
            drawLine(
                color = Color(0xFF26282D),
                start = Offset(0f, yPos),
                end = Offset(width, yPos),
                strokeWidth = 1f
            )
            drawLine(
                color = Color(0xFF26282D),
                start = Offset(0f, yPos + 0.2f * height),
                end = Offset(width, yPos + 0.2f * height),
                strokeWidth = 1f
            )
        }

        for (i in 0..(totalDuration / threeHourInterval).toInt()) {
            val x = i * intervalWidth
            drawLine(
                color = Color(0xFF38476C),
                start = Offset(x, 0f),
                end = Offset(x, height),
                strokeWidth = 1f, // 줄의 두께 설정
                pathEffect = PathEffect.chainPathEffect(
                    PathEffect.dashPathEffect(floatArrayOf(3f, 3f), 0f),
                    PathEffect.cornerPathEffect(2f)
                )
            )
        }

        for (i in 0..(totalDuration / threeHourInterval).toInt()) {
            val x = i * intervalWidth
            val hours = time + (i * 3) // 9시부터 시작한다고 가정
            val timeText = "${hours}시"
            /*
            * drawText는 Canvas에서 텍스트를 그리기 위해 사용되는 안드로이드의 기본 Canvas 객체를 사용하는 방법임.
            * 우리가 사용하는 Text를 사용하지 못함. 그러다보니 drawText를 사용해야함
            * */
            drawContext.canvas.nativeCanvas.drawText(timeText, x + 15f, height, android.graphics.Paint().apply{ textSize = 30f; color = Greyscale6.toArgb(); typeface = Typeface.create("wantedsans_medium", Typeface.NORMAL) })
        }
    }
}