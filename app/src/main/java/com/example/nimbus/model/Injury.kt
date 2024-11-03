package com.example.nimbus.model

import com.google.gson.annotations.SerializedName
import java.time.LocalDate
import java.util.UUID

data class Injury(
    val id: UUID,
    val type: String,
    @SerializedName("inicialDate") val initialDate: LocalDate,
    val finalDate: LocalDate
)