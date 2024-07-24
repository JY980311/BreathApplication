package com.example.breathapplication.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.breathapplication.ui.theme.Greyscale1
import com.example.breathapplication.ui.theme.Primary1
import com.example.breathapplication.ui.theme.Typography2

@Composable
fun CompleteButton(text: String){
    Button(
        modifier = Modifier
            .height(52.dp)
            .fillMaxWidth(),
        onClick = { },
        colors = ButtonDefaults.buttonColors(
            containerColor =  Primary1,
            contentColor = Greyscale1
        ),
        shape = RoundedCornerShape(6.dp)
    ) {
        Text(text = text,
            style = Typography2.button,
            textAlign = TextAlign.Center)
    }
}