package com.example.breathapplication.screen.main.main_component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.unit.dp
import com.example.breathapplication.ui.theme.Greyscale10
import com.example.breathapplication.ui.theme.Greyscale7
import com.example.breathapplication.ui.theme.Primary1
import com.example.breathapplication.ui.theme.Typography2
import kotlin.math.roundToInt

/** 수면 점수 그래프 */
@Composable
fun SleepScoreGraph(
    modifier: Modifier = Modifier,
    angle: Double,
) {

    val calPercentAngle = (angle * 100)
    val percentAngle = calPercentAngle * 3.6f

    val textCalPercentAngle = if (calPercentAngle == 0.0) {
        "NR"
    } else {
        calPercentAngle.roundToInt().toString() + "점"
    }

    Box(
        modifier = modifier
            .size(116.dp)
            .background(Greyscale7, CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawIntoCanvas {

                val borderWidth = 1.dp.toPx()
                val canvasSize = drawContext.size
                val strokeSize = 40f
                val adjustment =
                    borderWidth + strokeSize / 2 // stroke는 path의 중심을 따라(즉 border의 중심을 따라)그려지기 때문에 중심을 기준으로 반씩 그려지므로 반만큼 빼줘야함
                val adjustedSize = Size(
                    canvasSize.width - 2 * adjustment,
                    canvasSize.height - 2 * adjustment
                ) // 2를 곱하는 이유는 양쪽에 stroke가 그려지기 때문이다 즉 width 기준으로는 좌우, height 기준으로는 상하에 stroke가 그려진다.
                val drawCircleCenter = Offset(canvasSize.width / 2, canvasSize.height / 2)

                drawArc(
                    color = Primary1,
                    startAngle = -90f,
                    sweepAngle = -percentAngle.toFloat(),
                    useCenter = false,
                    topLeft = Offset(adjustment, adjustment),
                    size = adjustedSize,
                    style = Stroke(width = strokeSize, cap = StrokeCap.Butt)
                )

                val circleRadius =
                    minOf(canvasSize.width, canvasSize.height) / 2 - strokeSize // -> 해당 부분 *2 뺌

                drawCircle(
                    color = Greyscale10,
                    radius = circleRadius,
                    center = drawCircleCenter
                )
            }
        }

        Text(
            text = textCalPercentAngle,
            style = Typography2.title,
            color = Primary1
        )
    }
}