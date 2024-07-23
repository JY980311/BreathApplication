package com.example.breathapplication.network.test

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.breathapplication.network.model.GetSupabaseData
import com.example.breathapplication.network.retrofit.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TestViewModel: ViewModel() {
    private val _test = MutableStateFlow<List<GetSupabaseData>>(emptyList())
    val test: StateFlow<List<GetSupabaseData>> = _test.asStateFlow()

    init {
        getAllPostData()
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
}