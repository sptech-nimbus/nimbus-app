package com.example.nimbus.api

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
    val BASE_URL = "https://6642243c3d66a67b34366411.mockapi.io/nimbus/"
    //adicionar url da api na nuvem/local

    fun getRetrofit(context: Context): Retrofit {
        val sharedPref = context.getSharedPreferences("user_preferences", Context.MODE_PRIVATE)
        val token = sharedPref.getString("token", null) ?: ""

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient(token).build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getTeamsApi(context: Context): TeamsAPI = getRetrofit(context).create(TeamsAPI::class.java)
    fun getAthletesApi(context: Context): AthletesAPI = getRetrofit(context).create(AthletesAPI::class.java)
    fun getGamesAPI(context: Context): GamesAPI = getRetrofit(context).create(GamesAPI::class.java)
    fun getGameResultsAPI(context: Context): GameResultsAPI = getRetrofit(context).create(GameResultsAPI::class.java)
    fun getUsersApi(context: Context): UserAPI = getRetrofit(context).create(UserAPI::class.java)
}