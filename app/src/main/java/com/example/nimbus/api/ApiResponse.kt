package com.example.nimbus.api

data class ApiResponse<T>(
    val clientMsg: String?,
    val serverMsg: String?,
    val data: T
)