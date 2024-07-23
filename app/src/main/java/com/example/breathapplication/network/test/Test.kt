package com.example.breathapplication.network.test

import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import com.example.breathapplication.network.model.GetSupabaseDate
import com.example.breathapplication.network.model.PostSupabaseData
import com.example.breathapplication.network.retrofit.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@Composable
fun Test(
    testViewModel: TestViewModel
) {
    val test = testViewModel.test.collectAsStateWithLifecycle()

    Text(text = test.value.toString())
}

@Preview(showBackground = true)
@Composable
fun TestPreview() {
    Test(TestViewModel())
}