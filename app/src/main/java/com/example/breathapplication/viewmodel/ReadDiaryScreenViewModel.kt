package com.example.breathapplication.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

@RequiresApi(Build.VERSION_CODES.O)
class ReadDiaryScreenViewModel : ViewModel() {
    var name = mutableStateOf("홍길동")


}