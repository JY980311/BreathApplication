package com.example.breathapplication.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.breathapplication.R
import com.example.breathapplication.ui.theme.Greyscale10
import com.example.breathapplication.ui.theme.Greyscale5
import com.example.breathapplication.ui.theme.Greyscale7
import com.example.breathapplication.ui.theme.Primary1
import com.example.breathapplication.ui.theme.Typography2

@Composable
fun DisturbButton(tag: String) {
    var isClicked by remember { mutableStateOf(false) }
    var image = when(tag) {
        "과한 운동" -> R.drawable.ic_exercises
        "카페인" -> R.drawable.ic_coffee
        else -> R.drawable.ic_drink
    }
    Box(
        modifier = Modifier
            .width(98.dp)
            .height(72.dp)
            .border(1.dp, if (isClicked) Greyscale7 else Greyscale10, RoundedCornerShape(6.dp))
            .background(Greyscale10, RoundedCornerShape(6.dp))
            .clickable { isClicked = !isClicked },
    ) {
        Text(
            text = tag,
            style = Typography2.body2,
            color = if (isClicked) Primary1 else Greyscale5,
            modifier = Modifier.padding(12.dp, 14.dp)
        )

        Icon(
            painter = painterResource(id = image),
            contentDescription = "exercises",
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 14.dp, bottom = 13.dp),
            tint = Color.Unspecified,
        )
    }
}