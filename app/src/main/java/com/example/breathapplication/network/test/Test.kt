package com.example.breathapplication.network.test

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle

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