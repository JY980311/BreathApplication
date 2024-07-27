package com.example.breathapplication.network.model.asleep

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Stat(
    @Json(name = "breathing_index")
    val breathing_index: Double,
    @Json(name = "breathing_pattern")
    val breathing_pattern: String,
    @Json(name = "deep_latency")
    val deep_latency: Int,
    @Json(name = "deep_ratio")
    val deep_ratio: Double,
    @Json(name = "light_latency")
    val light_latency: Int,
    @Json(name = "light_ratio")
    val light_ratio: Double,
    @Json(name = "longest_waso")
    val longest_waso: Int,
    @Json(name = "no_snoring_ratio")
    val no_snoring_ratio: Double,
    @Json(name = "rem_latency")
    val rem_latency: Int,
    @Json(name = "rem_ratio")
    val rem_ratio: Double,
    @Json(name = "sleep_cycle")
    val sleep_cycle: Int,
    @Json(name = "sleep_cycle_count")
    val sleep_cycle_count: Int,
    @Json(name = "sleep_cycle_time")
    val sleep_cycle_time: List<String>,
    @Json(name = "sleep_efficiency")
    val sleep_efficiency: Double,
    @Json(name = "sleep_index")
    val sleep_index: Int,
    @Json(name = "sleep_latency")
    val sleep_latency: Int,
    @Json(name = "sleep_ratio")
    val sleep_ratio: Double,
    @Json(name = "sleep_time")
    val sleep_time: String,
    @Json(name = "snoring_count")
    val snoring_count: Int,
    @Json(name = "snoring_ratio")
    val snoring_ratio: Double,
    @Json(name = "stable_breath_ratio")
    val stable_breath_ratio: Double,
    @Json(name = "time_in_bed")
    val time_in_bed: Int,
    @Json(name = "time_in_deep")
    val time_in_deep: Int,
    @Json(name = "time_in_light")
    val time_in_light: Int,
    @Json(name = "time_in_no_snoring")
    val time_in_no_snoring: Int,
    @Json(name = "time_in_rem")
    val time_in_rem: Int,
    @Json(name = "time_in_sleep")
    val time_in_sleep: Int,
    @Json(name = "time_in_sleep_period")
    val time_in_sleep_period: Int,
    @Json(name = "time_in_snoring")
    val time_in_snoring: Int,
    @Json(name = "time_in_stable_breath")
    val time_in_stable_breath: Int,
    @Json(name = "time_in_unstable_breath")
    val time_in_unstable_breath: Int,
    @Json(name = "time_in_wake")
    val time_in_wake: Int,
    @Json(name = "unstable_breath_count")
    val unstable_breath_count: Int,
    @Json(name = "unstable_breath_ratio")
    val unstable_breath_ratio: Double,
    @Json(name = "wake_ratio")
    val wake_ratio: Double,
    @Json(name = "wake_time")
    val wake_time: String,
    @Json(name = "wakeup_latency")
    val wakeup_latency: Int,
    @Json(name = "waso_count")
    val waso_count: Int
)