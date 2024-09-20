package com.example.nimbus

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {
    val BASE_URL = "https://6642243c3d66a67b34366411.mockapi.io/nimbus/"

    fun getTeamsApi(): TeamsAPI {
        val cliente =
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(TeamsAPI::class.java)

        return cliente
    }

    fun getAthletesApi(): AthletesAPI {
        val cliente =
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(AthletesAPI::class.java)

        return cliente
    }
}