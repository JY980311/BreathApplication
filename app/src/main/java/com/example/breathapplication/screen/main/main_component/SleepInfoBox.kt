package com.example.breathapplication.screen.main.main_component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.breathapplication.screen.main.calculateTime
import com.example.breathapplication.ui.theme.Greyscale10
import com.example.breathapplication.ui.theme.Greyscale3
import com.example.breathapplication.ui.theme.Greyscale5
import com.example.breathapplication.ui.theme.Primary1
import com.example.breathapplication.ui.theme.Secondary1
import com.example.breathapplication.ui.theme.Typography2

/** 수면 정보 박스 */
@Composable
fun SleepInfoBox(
    modifier: Modifier = Modifier,
    title: String,
    sleepTime: Int
) {

    val mainColor = when (title) {
        "비수면" -> Color(0xFF8BAAF8)
        "얕은잠" -> Secondary1
        "깊은잠" -> Primary1
        "렘수면" -> Color(0xFF6746E9)
        else -> {
            Color.Red
        }
    }

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(color = Greyscale10)
            .padding(vertical = 10.dp, horizontal = 14.dp)
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(6.dp)
                        .clip(RoundedCornerShape(2.dp))
                        .background(mainColor)
                )

                Text(
                    text = title,
                    style = Typography2.body4,
                    color = Greyscale5
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = calculateTime(sleepTime),
                style = Typography2.body2,
                color = Greyscale3
            )
        }
    }
}