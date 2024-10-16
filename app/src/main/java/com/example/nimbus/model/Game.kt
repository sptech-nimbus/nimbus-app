package com.example.nimbus.model

import java.time.LocalDateTime
import java.util.UUID

data class Game(
    val id: String,
    val confirmed: Boolean,
    val inicialDateTime: LocalDateTime,
    val finalDateTime: LocalDateTime,
    val local: String?,
    val challenger: String,
    val challenged: String,
    val gameResult: GameResult?
)