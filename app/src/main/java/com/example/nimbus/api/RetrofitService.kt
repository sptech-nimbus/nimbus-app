package com.example.nimbus.api

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.ui.platform.LocalContext
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private fun okHttpClient(token: String) = OkHttpClient().newBuilder().addInterceptor(
    object : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val request : Request = chain.request()
                .newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
            return chain.proceed(request)
        }
    }
)

object RetrofitService {
    //val BASE_URL = "https://6642243c3d66a67b34366411.mockapi.io/nimbus/"
    val BASE_URL = "http://192.168.15.108:8080/"
    val token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ5dXJpLmNvYWNoQGVtYWlsLmNvbSIsImlhdCI6MTcyODc3NjUxOH0.qeGgzLjr26LWN1KHY9nIAEyP4QbtWRNBnNqokbjAEkGUuWOy5_eBZ5Mkgxod2LtluyGJ6ssmYdYcMGOY4L0H9w"

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient(token).build())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getTeamsApi(): TeamsAPI = retrofit.create(TeamsAPI::class.java)
    fun getAthletesApi(): AthletesAPI = retrofit.create(AthletesAPI::class.java)
    fun getGamesAPI(): GamesAPI = retrofit.create(GamesAPI::class.java)
    fun getGameResultsAPI(): GameResultsAPI = retrofit.create(GameResultsAPI::class.java)
    fun getUsersApi(): UserAPI = retrofit.create(UserAPI::class.java)
}