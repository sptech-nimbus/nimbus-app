package com.example.nimbus.api

import com.example.nimbus.model.Game
import com.example.nimbus.model.GameResult
import com.example.nimbus.model.Team
import java.time.LocalDateTime
import java.util.UUID

//times
val team1 = Team(
    UUID.randomUUID(),
    "Chicago Bulls",
    "Sub-19",
    "a",
    "Rua Haddock Lobo",
    2)

//resultados
val gameResultDerrota = GameResult("1", 50, 40, true)
val gameResultVitoria = GameResult("1", 40, 50, true)
val gameResultEmpate = GameResult("1", 50, 50, true)

//jogos
val game1 = Game(
    "1",
    true,
    LocalDateTime.now(),
    LocalDateTime.now(),
    "Rua Haddock Lobo",
    "1",
    "2",
    gameResult = gameResultVitoria
)

//