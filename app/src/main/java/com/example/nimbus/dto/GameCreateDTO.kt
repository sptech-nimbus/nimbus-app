package com.example.nimbus.dto

import java.time.LocalDate
import java.time.LocalDateTime

data class GameCreateDTO(
    val challenged: String,
    val challenger: String,
    val inicialDateTime: LocalDateTime,
    val finalDateTime: LocalDate
)