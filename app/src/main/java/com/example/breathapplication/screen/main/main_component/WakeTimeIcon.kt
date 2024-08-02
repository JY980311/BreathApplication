package com.example.breathapplication.screen.main.main_component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.breathapplication.R
import com.example.breathapplication.ui.theme.Greyscale8
import com.example.breathapplication.ui.theme.Primary1

/** 일어난 시간 아이콘 */
@Composable
fun WakeTimeIcon(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(28.dp)
            .clip(CircleShape)
            .background(Greyscale8)

    ) {
        Icon(
            modifier = Modifier.align(Alignment.Center),
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_sun),
            contentDescription = "취침 시간 아이콘",
            tint = Primary1
        )
    }
}
