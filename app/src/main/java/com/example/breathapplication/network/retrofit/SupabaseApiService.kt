package com.example.breathapplication.network.retrofit

import com.example.breathapplication.BuildConfig
import com.example.breathapplication.network.model.supabase.GetSupabaseData
import com.example.breathapplication.network.model.supabase.PostSupabaseData
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Query

interface SupabaseApiService : AsleepApiService {

    /** id에 맞는 데이터 받아오기 */
    @GET("Post")
    suspend fun getPostById(
        @Query("id") id: String,
        @Query("select") select: String = "*",
        @Query("apikey") apikey: String = BuildConfig.SUPABASE_API_KEY,
    ): List<GetSupabaseData>


    /** 데이터 받아오기 */
    @GET("Post")
    suspend fun getAllPost(
        @Query("select") select: String = "*",
        @Query("apikey") apikey: String = BuildConfig.SUPABASE_API_KEY,
    ) : List<GetSupabaseData>

    /** 데이터 보내기 */
    @Headers(
        "apikey: ${BuildConfig.SUPABASE_API_KEY}",
        "Authorization: Bearer ${BuildConfig.SUPABASE_API_KEY}",
        "Content-Type: application/json",
        "Prefer: return=representation"
    )
    @POST("Post")
    suspend fun createPost(
        @Query("apikey") apikey: String = BuildConfig.SUPABASE_API_KEY,
        @Body postSupabaseData: PostSupabaseData
    ) : Response<List<PostSupabaseData>>

    /** 같은 id가 있을 경우 데이터 업데이트 */
    @Headers(
        "apikey: ${BuildConfig.SUPABASE_API_KEY}",
        "Authorization: Bearer ${BuildConfig.SUPABASE_API_KEY}",
        "Content-Type: application/json",
        "Prefer: return=representation"
    )
    @PATCH("Post")
    suspend fun updatePost(
        @Query("id") id: String,
        @Query("apikey") apikey: String = BuildConfig.SUPABASE_API_KEY,
        @Body postSupabaseData: PostSupabaseData
    ): Response<List<PostSupabaseData>>
}