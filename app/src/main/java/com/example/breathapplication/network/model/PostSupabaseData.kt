package com.example.breathapplication.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PostSupabaseData(
    @Json(name = "id")
    val id: Int,
    @Json(name = "created_at")
    val created_at: String,
    @Json(name = "content")
    val content: String,
    @Json(name = "tag_1")
    val tag_1: Int,
    @Json(name = "tag_2")
    val tag_2: Int,
    @Json(name = "tag_3")
    val tag_3: Int,
    @Json(name = "graph")
    val graph: Int
)