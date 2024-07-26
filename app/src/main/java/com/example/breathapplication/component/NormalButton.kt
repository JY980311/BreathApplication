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
import com.example.breathapplication.ui.theme.Greyscale2
import com.example.breathapplication.ui.theme.Greyscale7
import com.example.breathapplication.ui.theme.Typography2

@Composable
fun NormalButton(text: String, onClick: () -> Unit){
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor =  Greyscale7,
            contentColor = Greyscale2
        ),
        shape = RoundedCornerShape(6.dp)
    ) {
        Text(
            text,
            style = Typography2.button,
            color = Greyscale2,
            textAlign = TextAlign.Center,
        )
    }
}