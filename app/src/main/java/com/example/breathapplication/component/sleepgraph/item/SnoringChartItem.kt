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
import androidx.compose.ui.tooling.preview.Preview
import com.example.breathapplication.component.sleepgraph.data.SnoringStageData
import com.example.breathapplication.ui.theme.Secondary1
enum class SnoringStageType {
    NONE, SNORING, ERROR
}

@Composable
fun SnoringChartItem(
    modifier: Modifier = Modifier,
    stages: List<SnoringStageData>,
    time: Int
) {
    val colors = mapOf(
        SnoringStageType.SNORING to Color(0xFF8BAAF8),
        SnoringStageType.NONE to Secondary1,
        SnoringStageType.ERROR to Color.Red
    )

    val stagePositions = mapOf(
        SnoringStageType.SNORING to 0.3f,
        SnoringStageType.NONE to 0.6f,
        SnoringStageType.ERROR to 0.9f,
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
                SnoringStageType.SNORING -> 0.3f * height
                SnoringStageType.NONE -> 0.6f * height
                SnoringStageType.ERROR -> 0.9f * height
            }

            val bottom = top + 0.2f * height

            drawRect(
                color = color,
                topLeft = Offset(left, top),
                size = Size(right - left, bottom - top),
                style = Fill
            )
        }


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

            val hours = time + (i * 3)
            val timeText = "${hours}시"
            drawContext.canvas.nativeCanvas.drawText(
                timeText,
                x + 15f,
                height - 10f,  // 텍스트 위치 조정
                android.graphics.Paint().apply {
                    textSize = 30f
                    color = Color.Gray.toArgb()
                    typeface = Typeface.create("wantedsans_medium", Typeface.NORMAL)
                }
            )
        }

        /*for (i in 0..(totalDuration / threeHourInterval).toInt()) {
            val x = i * intervalWidth
            val hours = time + (i * 3) // 9시부터 시작한다고 가정
            val timeText = "${hours}시"
            *//*
            * drawText는 Canvas에서 텍스트를 그리기 위해 사용되는 안드로이드의 기본 Canvas 객체를 사용하는 방법임.
            * 우리가 사용하는 Text를 사용하지 못함. 그러다보니 drawText를 사용해야함
            * *//*
            drawContext.canvas.nativeCanvas.drawText(timeText, x + 15f, height, android.graphics.Paint().apply{ textSize = 30f; color = Greyscale6.toArgb(); typeface = Typeface.create("wantedsans_medium", Typeface.NORMAL) })
        }*/
    }
}

@Preview(showBackground = true)
@Composable
fun SnoringChartItemPreview() {
    SnoringChartItem(
        stages = listOf(
 SnoringStageData(
                startTime = 0f,
                duration = 0.0083f,
                stage = SnoringStageType.SNORING
            ),
            SnoringStageData(
                startTime = 0.0083f,
                duration = 0.0083f,
                stage = SnoringStageType.NONE
            ),
            SnoringStageData(
                startTime = 0.0166f,
                duration = 0.0083f,
                stage = SnoringStageType.ERROR
            )
        ),
        time = 9
    )
}