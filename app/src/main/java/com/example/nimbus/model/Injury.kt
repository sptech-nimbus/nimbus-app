package com.example.nimbus.model

import java.time.LocalDate

data class Injury(
    val id: String,
    val type: String,
    val initialDate: LocalDate,
    val finalDate: LocalDate
)