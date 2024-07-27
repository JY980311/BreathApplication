package com.example.breathapplication.network.test

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.breathapplication.network.model.asleep.AsleepData
import com.example.breathapplication.network.model.asleep.Result
import com.example.breathapplication.network.model.asleep.Session
import com.example.breathapplication.network.model.asleep.Stat
import com.example.breathapplication.network.model.supabase.GetSupabaseData
import com.example.breathapplication.network.retrofit.RetrofitClient
import com.example.breathapplication.network.test.TestState.TestState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.time.TestTimeSource

class TestViewModel : ViewModel() {
    private val _test = MutableStateFlow<List<GetSupabaseData>>(emptyList())
    val test: StateFlow<List<GetSupabaseData>> = _test.asStateFlow()

    private val _asleepData = MutableStateFlow(TestState().asleepDataState)

    val asleepData: StateFlow<AsleepData> = _asleepData.asStateFlow()

    init {
        getAllAsleepData()
    }

    private fun getAllPostData() {
        viewModelScope.launch {
            val apiService = RetrofitClient.getSupabaseApiService()
            try {
                val apiResponse: List<GetSupabaseData> = apiService.getAllPost()
                _test.value = apiResponse
            } catch (e: Exception) {
                Log.d("PostViewModel", "error: ${e}")
            }
        }
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
