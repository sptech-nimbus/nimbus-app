package com.example.nimbus.model

import java.util.UUID

data class User(
    val id: UUID,
    val email: String,
    val password: String,
    val athlete: Athlete?,
    val coach: Coach?
)