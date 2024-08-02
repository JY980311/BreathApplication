package com.example.breathapplication.screen.main.main_component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.example.breathapplication.ui.theme.Greyscale8
import kotlin.math.sin

/** Wave Composable 웨이브 모양 */
@Composable
fun Wave(
    modifier: Modifier = Modifier
) {
    Canvas(
        modifier = modifier
            .fillMaxWidth()
            .height(6.dp)
    ) {

        val path = Path()
        val waveHeight = 5f  // 물결의 높이
        val waveLength = size.width / 16  // 물결 한 주기의 길이

        path.moveTo(0f, size.height / 2)

        var x = 0f

        while (x < size.width) {
            val y = (size.height / 2) + waveHeight * sin((x / waveLength) * (2 * Math.PI)).toFloat()
            path.lineTo(x, y)
            x += 1f
        }

        drawPath(
            path = path,
            color = Greyscale8,
            style = Stroke(width = 1f)  // 선의 두께 조정
        )
    }
}