package com.example.nimbus.api

import com.example.nimbus.dto.UserCreateDTO
import com.example.nimbus.dto.UserLoginDTO
import com.example.nimbus.dto.UserLoginResponseDTO
import com.example.nimbus.model.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import java.util.UUID

interface UserAPI {
    @GET("/users/{id}")
    suspend fun getById(@Path("id") id: UUID): Response<ApiResponse<User>>

    @POST("/users")
    suspend fun post(@Body userCreateDTO: UserCreateDTO): Response<ApiResponse<User>>

    @POST("/users/login")
    suspend fun login(@Body userLoginDTO: UserLoginDTO): Response<ApiResponse<UserLoginResponseDTO>>
}