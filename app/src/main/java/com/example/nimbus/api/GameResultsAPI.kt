package com.example.nimbus.api

import com.example.nimbus.dto.GameResultCreateDTO
import com.example.nimbus.model.GameResult
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import java.util.UUID

interface GameResultsAPI {
    @POST("/game-results")
    suspend fun post(@Body gameResult: GameResultCreateDTO): Response<ApiResponse<GameResult>>

    @GET("/game-results/not-confirmed-results/{id}")
    suspend fun getNotConfirmed(@Path("id") id: UUID): Response<ApiResponse<List<GameResult>>>

    @PATCH("/game-results/confirm-game-result/{id}")
    suspend fun confirm(@Path("id") id: UUID): Response<ApiResponse<GameResult>>
}