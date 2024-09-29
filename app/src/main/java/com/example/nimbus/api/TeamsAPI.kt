package com.example.nimbus.api

import com.example.nimbus.model.Team
import retrofit2.Response
import retrofit2.http.GET

interface TeamsAPI {
    @GET("/teams")
    suspend fun getAllTeams(): Response<List<Team>>

    @GET("/teams/{id}")
    suspend fun getTeams(): Response<Team>


}