package com.example.breathapplication.calendar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.breathapplication.R
import com.example.breathapplication.ui.theme.Greyscale5
import com.example.breathapplication.ui.theme.Greyscale6
import com.example.breathapplication.ui.theme.Typography2

@Composable
fun Header() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            modifier = Modifier.clickable {  },
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_left_arrow),
            contentDescription = "왼쪽 화살표",
            tint = Greyscale6
        )

        //TODO: 월 별로 변경하게 하기
        Text(
            text = "7월",
            style = Typography2.subTitle,
            color = Greyscale5
        )

        Icon(
            modifier = Modifier.clickable {  },
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_right_arrow),
            contentDescription = "오른쪽 화살표",
            tint = Greyscale6
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HeaderPreview() {
    Header()
}