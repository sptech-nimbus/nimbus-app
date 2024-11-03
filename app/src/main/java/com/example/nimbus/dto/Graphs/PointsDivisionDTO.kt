package com.example.nimbus.dto.Graphs

import com.google.gson.annotations.SerializedName

data class PointsDivisionDTO(
    @SerializedName("twoPointsDivision") val twoPoints: Double,
    @SerializedName("threePointsDivision") val threePoints: Double
)