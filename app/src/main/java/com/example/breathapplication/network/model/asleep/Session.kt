package com.example.breathapplication.network.model.asleep

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Session(
    @Json(name = "breath_stages")
    val breath_stages: List<Int>,
    @Json(name = "created_timezone")
    val created_timezone: String,
    @Json(name = "end_time")
    val end_time: String,
    @Json(name = "id")
    val id: String,
    @Json(name = "sleep_stages")
    val sleep_stages: List<Int>,
    @Json(name = "snoring_stages")
    val snoring_stages: List<Int>,
    @Json(name = "start_time")
    val start_time: String,
    @Json(name = "state")
    val state: String,
    @Json(name = "unexpected_end_time")
    val unexpected_end_time: Any?
)