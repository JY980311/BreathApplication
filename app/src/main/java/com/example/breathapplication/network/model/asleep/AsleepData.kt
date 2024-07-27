package com.example.breathapplication.network.model.asleep

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AsleepData(
    @Json(name = "detail")
    val detail: String,
    @Json(name = "result")
    val result: Result
)