package com.example.breathapplication.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.breathapplication.ui.theme.Greyscale10
import com.example.breathapplication.ui.theme.Greyscale8
import com.example.breathapplication.ui.theme.Primary2
import com.example.breathapplication.ui.theme.Typography2

@Composable
fun MySleepTag(tag: String) {
    Box(
        modifier = Modifier
            .height(33.dp)
            .wrapContentWidth(align = Alignment.Start)
            .border(1.dp, Greyscale10, RoundedCornerShape(6.dp))
            .background(Greyscale8, RoundedCornerShape(6.dp))
    ) {
        Text(
            text = tag,
            style = Typography2.body2,
            color = Primary2,
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp),
        )
    }
}