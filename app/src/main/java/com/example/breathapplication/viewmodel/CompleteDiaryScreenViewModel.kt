package com.example.breathapplication.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class CompleteDiaryScreenViewModel : ViewModel(){
    var text by mutableStateOf("")
}