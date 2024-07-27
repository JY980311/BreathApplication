package com.example.breathapplication.network.test

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun Test(
    testViewModel: TestViewModel
) {
    val test = testViewModel.test.collectAsStateWithLifecycle()
    val asleepData = testViewModel.asleepData.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        //Text(text = test.value.toString())
        Text(text = asleepData.value.toString())
    }
}

@Preview(showBackground = true)
@Composable
fun TestPreview() {
    Test(TestViewModel())
}