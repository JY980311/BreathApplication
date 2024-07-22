package com.example.breathapplication.calendar.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.breathapplication.ui.theme.Greyscale10
import com.example.breathapplication.ui.theme.Greyscale5
import com.example.breathapplication.ui.theme.Greyscale6
import com.example.breathapplication.ui.theme.Typography2

@Composable
fun ContentItem(
    day: String,
    date: String,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(10.79.dp),
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
    ) {
        Text(
            text = day,
            style = Typography2.subTitle,
            color = Greyscale6
        )
        Text(
            text = date,
            style = Typography2.subTitle,
            color = Greyscale5
        )
    }
}

@Composable
fun Content() {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        items(items = List(7) { Pair("월", "17") }) { date ->
            ContentItem(date.first, date.second)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ContentPreview() {
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(132.dp)
        .background(Greyscale10)
        .padding(20.dp)
    ){
        Column {
            Header()
            Spacer(modifier = Modifier.height(12.dp))
            Content()
        }
    }
}