package com.example.nimbus.api

import com.example.nimbus.model.AthleteDesc
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.UUID

interface AthleteDescsAPI {
    @GET("/athlete-descs/{id}")
    suspend fun getAthleteDesc(@Path("id") id: UUID): Response<ApiResponse<AthleteDesc>>
}