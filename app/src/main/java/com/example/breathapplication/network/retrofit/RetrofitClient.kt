package com.example.breathapplication.network.retrofit

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClient {
    companion object { // 객체 생성 없이 사용할 수 있도록 함

        private const val SUPABASE_URL = "https://ebuvlzafrddkrqjhhvdm.supabase.co/rest/v1/"

        private var supabaseService: SupabaseApiService? = null // supabaseApiService 인터페이스를 사용할 수 있도록 선언

        fun getSupabaseApiService(): SupabaseApiService {
            if (supabaseService == null) {

                val okHttpClient = OkHttpClient.Builder()
                    .connectTimeout(15, TimeUnit.SECONDS)
                    .readTimeout(15, TimeUnit.SECONDS)
                    .writeTimeout(15, TimeUnit.SECONDS)
                    .build()

                val moshi = Moshi.Builder()
                    .add(KotlinJsonAdapterFactory())
                    .build()

                supabaseService = Retrofit.Builder()
                    .baseUrl(SUPABASE_URL)
                    .addConverterFactory(MoshiConverterFactory.create(moshi))
                    .client(okHttpClient)
                    .build()
                    .create(SupabaseApiService::class.java) // IApiService 인터페이스를 사용할 수 있도록 생성
            }
            return supabaseService!! // apiService가 null이 아닌 경우 반환
        }
    }
}