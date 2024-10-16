package com.example.nimbus.model

import java.time.LocalDate
import java.util.UUID

data class Coach(
    val id: UUID,
    val firstName: String,
    val lastName: String,
    val birthDate: LocalDate,
    val phone: String,
    val picture: String
)