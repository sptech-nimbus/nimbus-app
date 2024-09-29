package com.example.nimbus.api

import com.example.nimbus.model.Athlete
import retrofit2.Response
import retrofit2.http.GET

interface AthletesAPI {
    @GET("/athletes")
    suspend fun getAllAthletes(): Response<List<Athlete>>

    @GET("/athletes/{id}")
    suspend fun getAthlete(): Response<Athlete>
}