package com.example.breathapplication.network.model.supabase

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * GET Post 설명
 *
 *    val content: String, 내용 부분
 *    val tag_1: Int, 태그 1 부분 ex) 1:과한운동, 2:과음, 3:카페인
 *    val tag_2: Int, 태그 2 부분 ex) 1:행복해요, 2:편안해요, 3:내일이 기대돼요, 4:잠이 솔솔와요, 5:그냥 그래요, 6:슬퍼요, 7:지쳤어요, 8:걱정이 많아요, 9:불안해요
 *    val tag_3: Int, 태그 3 부분 ex) 1:개운했어요, 2:피곤했어요
 *    val graph: Int  그래프 부분 ex) 1:BAD, 2:BAD~SOSO, 3:SOSO, 4:SOSO~GOOD, 5:GOOD
 *    추가 부분 : 요약 부분이 있습니다 해당 부분은 그냥 String으로 해서 받을지 어떻게 할까요? -> 만약에 String으로 받는 다면 Supabase 테이블 수정하겠습니다
 *
 * */

@JsonClass(generateAdapter = true)
data class GetSupabaseData(
    @Json(name = "id")
    val id: Int,
    @Json(name = "content")
    val content: String,
    @Json(name = "created_at")
    val created_at: String,
    @Json(name = "tag_1")
    val tag_1: Int,
    @Json(name = "tag_2")
    val tag_2: Int,
    @Json(name = "tag_3")
    val tag_3: Int,
    @Json(name = "graph")
    val graph: Int
)

