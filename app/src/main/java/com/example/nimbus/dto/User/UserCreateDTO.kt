package com.example.nimbus.dto.User

import com.example.nimbus.model.Athlete
import com.example.nimbus.model.Coach

data class UserCreateDTO(
    val email: String,
    val password: String,
    val coach: Coach?,
    val athlete: Athlete?
)