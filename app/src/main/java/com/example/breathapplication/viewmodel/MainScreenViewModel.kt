package com.example.breathapplication.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.breathapplication.network.model.asleep.AsleepData
import com.example.breathapplication.network.model.supabase.GetSupabaseData
import com.example.breathapplication.network.retrofit.RetrofitClient
import com.example.breathapplication.network.test.TestState.TestState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainScreenViewModel: ViewModel() {
    private val _asleepData = MutableStateFlow(TestState().asleepDataState)

    val asleepData: StateFlow<AsleepData> = _asleepData.asStateFlow()

    init {
        getAllAsleepData()
    }

    fun getAllAsleepData() {
        viewModelScope.launch {
            val apiService = RetrofitClient.getAsleepApiService()
            try {
                val apiResponse: AsleepData = apiService.getAsleepData()
                _asleepData.value = apiResponse
            } catch (e: Exception) {
                Log.d("PostViewModel - Asleep", "error: ${e.message}")
            }
        }
    }
}