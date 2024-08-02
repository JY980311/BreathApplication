package com.example.breathapplication.screen.main.main_component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.breathapplication.ui.theme.Greyscale1
import com.example.breathapplication.ui.theme.Primary1
import com.example.breathapplication.ui.theme.Typography2

/** 수면 수직 그래프 */
@Composable
fun SleepVerticalGraph(
    modifier: Modifier = Modifier,
    size: Double
) {
    Box(
        modifier = modifier
            .width(47.dp)
            .height(30.dp + (size * 100).dp)
            .clip(
                RoundedCornerShape(
                    topStart = 12.dp,
                    topEnd = 12.dp,
                    bottomStart = 2.dp,
                    bottomEnd = 2.dp
                )
            )
            .background(Primary1)
    ) {
        Text(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp)
                .padding(top = 10.dp),
            text = "${(size * 100).toInt()}%",
            style = Typography2.body3,
            color = Greyscale1,
            textAlign = TextAlign.Center
        )
    }
}
