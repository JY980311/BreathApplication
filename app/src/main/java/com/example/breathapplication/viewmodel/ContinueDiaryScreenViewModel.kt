package com.example.breathapplication.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ContinueDiaryScreenViewModel : ViewModel() {
    var showDialog by mutableStateOf(false)
}