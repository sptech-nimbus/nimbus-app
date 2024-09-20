package com.example.nimbus.model

data class Team(
    val id: String,
    val name: String,
    val category: String?,
    val picture: String?,
    val local: String?,
    //val athletes: List<Athlete>?,
    val level: Int
)