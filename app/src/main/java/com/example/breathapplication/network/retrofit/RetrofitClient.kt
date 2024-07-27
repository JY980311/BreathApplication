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
        private const val ASLEEP_URL = "https://api.asleep.ai/"

        private var supabaseService: SupabaseApiService? = null // supabaseApiService 인터페이스를 사용할 수 있도록 선언
        private var asleepApiService: AsleepApiService? = null

        /**
         * SupabaseApiService 반환하는 메소드!
         * */
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

        /**
         * AsleepApiService 반환하는 메소드
         * 일단은 1개의 데이터만 받아오게 했습니다!
         * 데이터가 많으면 다 가져오는게 아닌 특정 데이터 값 1개의 값만 가져옵니다!
         * */
        fun getAsleepApiService(): AsleepApiService {
            if (asleepApiService == null) {

                val okHttpClient = OkHttpClient.Builder()
                    .connectTimeout(15, TimeUnit.SECONDS)
                    .readTimeout(15, TimeUnit.SECONDS)
                    .writeTimeout(15, TimeUnit.SECONDS)
                    .build()

                val moshi = Moshi.Builder()
                    .add(KotlinJsonAdapterFactory())
                    .build()

                asleepApiService = Retrofit.Builder()
                    .baseUrl(ASLEEP_URL)
                    .addConverterFactory(MoshiConverterFactory.create(moshi))
                    .client(okHttpClient)
                    .build()
                    .create(AsleepApiService::class.java) // IApiService 인터페이스를 사용할 수 있도록 생성
            }
            return asleepApiService!! // apiService가 null이 아닌 경우 반환
        }
    }
}