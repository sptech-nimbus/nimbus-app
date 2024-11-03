package com.example.nimbus.api.interfaces

import com.example.nimbus.api.ApiResponse
import com.example.nimbus.model.Team
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.UUID

interface TeamsAPI {
    @GET("/teams")
    //suspend fun getAllTeams(): Response<List<Team>>
    suspend fun getAllTeams(): Response<ApiResponse<List<Team>>>

    @GET("/teams/{id}")
    suspend fun getTeams(@Path("id") id: UUID): Response<ApiResponse<Team>>
    
    @GET("/teams/by-coach/{id}")
    suspend fun getAllByCoach(@Path("id") id: UUID): Response<ApiResponse<List<Team>>>
}