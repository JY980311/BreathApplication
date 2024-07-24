package com.example.breathapplication.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.breathapplication.R
import com.example.breathapplication.ui.theme.Greyscale10
import com.example.breathapplication.ui.theme.Greyscale3
import com.example.breathapplication.ui.theme.Greyscale7
import com.example.breathapplication.ui.theme.Primary1
import com.example.breathapplication.ui.theme.Typography2
import kotlin.math.abs
import kotlin.math.roundToInt

@Preview(showSystemUi = true)
@Composable
fun SlideButton() {
    var offsetX by remember { mutableStateOf(0f) }
    var barWidth by remember { mutableStateOf(0f) }
    val buttonSize = 28.dp
    val density = LocalDensity.current.density
    val buttonSizePx = buttonSize.toPx(density)
    var maxWidth = 0.0f

    // 원 반지름과 마진
    val pointRadius = 6.dp.toPx(density)
    val pointMargin = 5.dp.toPx(density)

    // 원 위치
    val points = remember { mutableStateListOf<Float>() }
    var startPosition by remember { mutableStateOf(0f) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 20.dp) // 테스트용 padding, 제거 후 사용
    ) {
        Box {
            Canvas(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(16.dp)
                    .align(Alignment.Center)
                    .onGloballyPositioned { coordinates ->
                        barWidth = coordinates.size.width.toFloat()
                        points.clear()
                        points.addAll(
                            listOf(
                                pointRadius + pointMargin, // 첫 번째 원 위치
                                barWidth / 4f, // 두 번째 원 위치
                                barWidth / 2f, // 세 번째 원 위치
                                3 * barWidth / 4f, // 네 번째 원 위치
                                barWidth - pointRadius - pointMargin // 마지막 원 위치
                            )
                        )
                    }
            ) {
                maxWidth = size.width
                drawRoundRect(
                    color = Greyscale10,
                    size = Size(width = size.width, height = 16.dp.toPx(density)),
                    cornerRadius = CornerRadius(32.dp.toPx(density), 32.dp.toPx(density))
                )
                drawRoundRect(
                    color = Primary1,
                    size = Size(
                        width = (offsetX + buttonSizePx / 2).coerceIn(0f, size.width),
                        height = 16.dp.toPx(density)
                    ),
                    cornerRadius = CornerRadius(32.dp.toPx(density), 32.dp.toPx(density))
                )

                points.forEach { x ->
                    val pointColor = if (x <= offsetX + buttonSizePx / 2) Greyscale3 else Greyscale7
                    drawCircle(
                        color = pointColor,
                        radius = pointRadius,
                        center = Offset(x, size.height / 2)
                    )
                }
            }

            Box(
                modifier = Modifier
                    .offset { IntOffset((offsetX).roundToInt(), 0) }
                    .pointerInput(Unit) {
                        detectDragGestures(
                            onDragStart = { startPosition = offsetX  },
                            onDrag = { change, dragAmount ->
                                change.consume()
                                val newOffsetX =
                                    (offsetX + dragAmount.x).coerceIn(0f, maxWidth - buttonSizePx)
                                offsetX = newOffsetX
                            },
                            onDragEnd = {
                                val closestPoint = points.filter { it <= offsetX }
                                    .minByOrNull { abs(it - offsetX) }
                                    ?: points.minByOrNull { abs(it - offsetX) }
                                    ?: startPosition

                                // 마지막 원의 위치
                                val lastPoint = points.last()

                                // 마지막 원이 가장 가까운 경우
                                if (abs(offsetX - lastPoint) < abs(offsetX - closestPoint)) {
                                    offsetX = lastPoint - buttonSizePx / 2
                                } else {
                                    offsetX = closestPoint - buttonSizePx / 2
                                }
                            }
                        )
                    }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_moon),
                    contentDescription = "moon",
                    tint = Color.Unspecified,
                    modifier = Modifier
                        .size(buttonSize)
                )
            }
        }

        Spacer(modifier = Modifier.height(9.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("BAD", style = Typography2.body4, color = Primary1)
            Text("SOSO", style = Typography2.body4, color = Primary1)
            Text("GOOD!", style = Typography2.body4, color = Primary1)
        }
    }
}

private fun Dp.toPx(density: Float): Float {
    return this.value * density
}