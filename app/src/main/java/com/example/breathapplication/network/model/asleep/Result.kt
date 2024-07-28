package com.example.breathapplication.network.model.asleep

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Result(
    @Json(name = "missing_data_ratio")
    val missing_data_ratio: Double,
    @Json(name = "peculiarities")
    val peculiarities: List<Any>,
    @Json(name = "session")
    val session: Session,
    @Json(name = "stat")
    val stat: Stat,
    @Json(name = "timezone")
    val timezone: String
)