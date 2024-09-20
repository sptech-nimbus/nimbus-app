package com.example.nimbus.model

data class Athlete(
    val id: String,
    val firstName: String,
    val lastName: String,
    val birthDate: String,
    val phone: String,
    val picture: String?,
    val category: String,
    val isStarting: Boolean,
    val isInjuried: Boolean,
    //val athleteDesc: AthleteDesc, //descomentar para a implementação com a API real
    //val injuries: List<Injury> //descomentar para a implementação com a API real
    val position: String, //remover para a implementação com a API real
)