package com.example.breathapplication.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.example.breathapplication.ui.theme.Greyscale9
import com.example.breathapplication.ui.theme.Primary1
import com.example.breathapplication.ui.theme.Typography2

@Composable
fun ConditionButton(tag : String, isSelected: Boolean, onClick: () -> Unit){
    var isClicked by remember { mutableStateOf(false) }
    var image = when(tag) {
        "개운했어요" -> if(isSelected) R.drawable.ic_fresh_blue else R.drawable.ic_fresh_grey
        else -> if(isSelected) R.drawable.ic_tired_blue else R.drawable.ic_tired_grey
    }

    Box(
        modifier = Modifier
            .width(102.dp)
            .height(110.dp)
            .border(1.dp, if (isSelected) Greyscale7 else Greyscale10, RoundedCornerShape(6.dp))
            .background(if(isSelected) Greyscale9 else Greyscale10, RoundedCornerShape(6.dp))
            .clickable { onClick() },

    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = tag,
                style = Typography2.body2,
                color = if (isSelected) Primary1 else Greyscale5,
                modifier = Modifier.padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 6.dp),
            )

            Icon(
                painter = painterResource(id = image),
                contentDescription = "condition",
                modifier = Modifier
                    .padding(start = 11.dp, end = 11.dp, bottom = 16.dp),
                tint = Color.Unspecified,
            )
        }

    }
}