package com.example.nimbus.api

import com.example.nimbus.api.interfaces.AthletesAPI
import com.example.nimbus.api.interfaces.GameResultsAPI
import com.example.nimbus.api.interfaces.GamesAPI
import com.example.nimbus.api.interfaces.GraphsAPI
import com.example.nimbus.api.interfaces.TeamsAPI
import com.example.nimbus.api.interfaces.UserAPI
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
    //val BASE_URL = "https://6642243c3d66a67b34366411.mockapi.io/nimbus/" //ip mock
    val BASE_URL = "http://192.168.15.108:8080/" //ip casa

    //val token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ5dXJpLmNvYWNoQGVtYWlsLmNvbSIsImlhdCI6MTcyODc3NjUxOH0.qeGgzLjr26LWN1KHY9nIAEyP4QbtWRNBnNqokbjAEkGUuWOy5_eBZ5Mkgxod2LtluyGJ6ssmYdYcMGOY4L0H9w"

    fun getRetrofit(token: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient(token).build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getTeamsApi(token: String): TeamsAPI = getRetrofit(token).create(TeamsAPI::class.java)
    fun getAthletesApi(token: String): AthletesAPI = getRetrofit(token).create(AthletesAPI::class.java)
    fun getGamesApi(token: String): GamesAPI = getRetrofit(token).create(GamesAPI::class.java)
    fun getGameResultsApi(token: String): GameResultsAPI = getRetrofit(token).create(GameResultsAPI::class.java)
    fun getUsersApi(token: String): UserAPI = getRetrofit(token).create(UserAPI::class.java)
    fun getGraphApi(token: String): GraphsAPI = getRetrofit(token).create(GraphsAPI::class.java)
}