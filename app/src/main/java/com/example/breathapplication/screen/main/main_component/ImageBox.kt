package com.example.breathapplication.screen.main.main_component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun ImageBox(
    modifier: Modifier = Modifier,
    image: Int
) {
    Box(
        modifier = modifier
            .size(150.dp, 160.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFF272B33))
    ) {
        Image(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxSize()
                .clip(RoundedCornerShape(12.dp)),
            painter = painterResource(id = image),
            contentDescription = "상품 이미지",
            contentScale = ContentScale.FillBounds
        )
    }
}
