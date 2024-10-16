package com.example.nimbus.api

import com.example.nimbus.dto.GameCreateDTO
import com.example.nimbus.model.Game
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import java.util.UUID

interface GamesAPI {
    @GET("/games/{id}")
    suspend fun getGamesByTeam(@Path("id") id: UUID): Response<ApiResponse<List<Game>>>

    @POST("/games")
    suspend fun post(@Body gameCreateDTO: GameCreateDTO): Response<ApiResponse<Game>>

    @PATCH("/games/confirm-game/{id}")
    suspend fun confirmGame(@Path("id") id: UUID): Response<ApiResponse<Game>>
}