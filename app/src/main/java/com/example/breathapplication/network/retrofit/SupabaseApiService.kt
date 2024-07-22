package com.example.breathapplication.network.retrofit

import com.example.breathapplication.network.model.GetSupabaseDate
import com.example.breathapplication.network.model.PostSupabaseData
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface SupabaseApiService {
    /** TODO: 꼭 local.properties로 빼기 */
    @GET("Post")
    suspend fun getAllPost(
        @Query("select") select: String = "*",
        @Query("apikey") apikey: String = "api키값으로 넣으세요!",
    ) : List<GetSupabaseDate>
    @Headers(
        "apikey: api키값으로 넣으세요!",
        "Authorization: Bearer api키값으로 넣으세요!",
        "Content-Type: application/json",
        "Prefer: return=representation"
    )
    @POST("Post")
    suspend fun createPost(
        @Query("apikey") apikey: String = "api키값으로 넣으세요!",
        @Body postSupabaseData: PostSupabaseData
    ) : Response<List<PostSupabaseData>>
}