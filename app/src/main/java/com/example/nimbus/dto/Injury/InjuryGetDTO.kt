package com.example.nimbus.dto.Injury

import com.google.gson.annotations.SerializedName
import java.util.UUID

data class InjuryGetDTO(
    val id: UUID,
    val type: String,
    @SerializedName("inicialDate") val initialDate: String,
    val finalDate: String
)
