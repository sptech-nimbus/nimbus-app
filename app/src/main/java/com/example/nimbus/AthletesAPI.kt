package com.example.nimbus

import com.example.nimbus.model.Athlete
import com.example.nimbus.model.Team
import retrofit2.Response
import retrofit2.http.GET

interface AthletesAPI {
    @GET("/athletes")
    suspend fun getAllAthletes(): Response<List<Athlete>>

    @GET("/athletes/{id}")
    suspend fun getAthlete(): Response<Athlete>
}