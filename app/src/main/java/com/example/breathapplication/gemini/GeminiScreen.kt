package com.example.breathapplication.gemini

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun GeminiScreen(
    viewModel: GeminiViewModel
) {
   val textResult = viewModel.textGenerationResult.collectAsStateWithLifecycle()
    Column {
        Button(onClick = {
            viewModel.generateText()
        }) {
            Text("Generate Text")
        }
        
        Text(text = textResult.value ?: "No text generated yet")
    }
}

@Preview(showBackground = true)
@Composable
fun GeminiScreenPreview() {
    GeminiScreen(GeminiViewModel())
}