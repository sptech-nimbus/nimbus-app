package com.example.nimbus.model

data class GameResult(
    val id: String,
    val challengerPoints: Int,
    val chalengedPoints: Int,
    val confirmed: Boolean
)