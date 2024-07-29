package com.example.breathapplication.component

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.breathapplication.ui.theme.Greyscale10
import com.example.breathapplication.ui.theme.Greyscale4
import com.example.breathapplication.ui.theme.Typography2
import com.example.breathapplication.viewmodel.DiaryScreenViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun InputDiaryText(diaryScreenViewModel: DiaryScreenViewModel) {
    val writeDiaryText by diaryScreenViewModel.writeDiaryText

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(130.dp)
            .background(Greyscale10, RoundedCornerShape(6.dp))
            .border(1.dp, Greyscale10, RoundedCornerShape(6.dp))
            .padding(16.dp, 23.dp, 18.dp, 23.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        BasicTextField(
            value = writeDiaryText,
            onValueChange = { diaryScreenViewModel.setWriteDiaryText(it) },
            textStyle = Typography2.bodyText.copy(color = Greyscale4),
            decorationBox = { innerTextField ->
                Text(
                    text = if (diaryScreenViewModel.writeDiaryText.value == "") "오늘 하루는 어땠나요?" else "",
                    color = Greyscale4,
                    style = Typography2.bodyText,
                    modifier = Modifier.fillMaxSize()
                )
                innerTextField()
            })
    }
}