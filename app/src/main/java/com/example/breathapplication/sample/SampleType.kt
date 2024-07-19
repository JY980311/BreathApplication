package com.example.breathapplication.sample

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.breathapplication.ui.theme.Greyscale10
import com.example.breathapplication.ui.theme.Greyscale11
import com.example.breathapplication.ui.theme.Greyscale9
import com.example.breathapplication.ui.theme.Primary1
import com.example.breathapplication.ui.theme.Primary2
import com.example.breathapplication.ui.theme.Secondary1
import com.example.breathapplication.ui.theme.Secondary2
import com.example.breathapplication.ui.theme.Typography2

/** 폰트 사용법 입니다! 참고 하세요! */
@Composable
fun SampleType() {
    Column(
        modifier = Modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            text = "H1",
            style = Typography2.h1
        )
        Text(
            text = "Subhead",
            style = Typography2.subHead
        )
        Text(
            text = "Title",
            style = Typography2.title
        )
        Text(
            text = "Subtitle",
            style = Typography2.subTitle
        )
        Text(
            text = "Body-text",
            style = Typography2.bodyText
        )
        Text(
            text = "Body1",
            style = Typography2.body1
        )
        Text(
            text = "Body2",
            style = Typography2.body2
        )
        Text(
            text = "Body3",
            style = Typography2.body3
        )
        Text(
            text = "Body4",
            style = Typography2.body4
        )
        Text(
            text = "Button",
            style = Typography2.button
        )
        Text(
            text = "caption",
            style = Typography2.caption
        )
    }
}

/** color 사용법 입니다! 참고 하세요! */
@Composable
fun TestColor() {
    Column(
        modifier = Modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            text = "H1",
            style = Typography2.h1,
            color = Primary1
        )
        Text(
            text = "Subhead",
            style = Typography2.subHead,
            color = Primary2
        )
        Text(
            text = "Title",
            style = Typography2.title,
            color = Secondary1
        )
        Text(
            text = "Subtitle",
            style = Typography2.subTitle,
            color = Secondary2
        )
        Text(
            text = "Body-text",
            style = Typography2.bodyText,
            color = Greyscale11
        )
        Text(
            text = "Body1",
            style = Typography2.body1,
            color = Greyscale10
        )
        Text(
            text = "Body2",
            style = Typography2.body2,
            color = Greyscale9
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TextTestPreview() {
    MaterialTheme {
        SampleType()
    }
}

@Preview(showBackground = true)
@Composable
fun ColorTestPreview() {
    MaterialTheme {
        TestColor()
    }
}