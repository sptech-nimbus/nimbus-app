package com.example.nimbus.dto.Game

import java.util.UUID

data class GameID(
    val id: UUID
)

data class GameResultCreateDTO(
    val challengerPoints: Int,
    val challengedPoints: Int,
    val game: GameID
)