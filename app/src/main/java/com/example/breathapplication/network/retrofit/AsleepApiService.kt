package com.example.breathapplication.network.retrofit

import com.example.breathapplication.BuildConfig
import com.example.breathapplication.network.model.asleep.AsleepData
import retrofit2.http.GET
import retrofit2.http.Headers


/**
 * https://api.asleep.ai/data/v3/sessions/20240722173935_s6t5u 싱글 데이터 요청
 * https://api.asleep.ai/ai/v1/users/G-20240719044150-dwrKhJQNBRedJyOJfZTp 세션 아이디 요청
 * */
interface AsleepApiService {
    @Headers(
        "x-api-key: ${BuildConfig.ASLLEP_API_KEY}",
        "x-user-id: G-20240719044150-dwrKhJQNBRedJyOJfZTp"
    )
    /** 20240722173935_s6t5u 해당 부분은 User의 세션 ID! 일단은 임시로 제가 측정했던 세션 ID 넣었습니다. */
    @GET("data/v3/sessions/20240722173935_s6t5u")
    suspend fun getAsleepData() : AsleepData
}