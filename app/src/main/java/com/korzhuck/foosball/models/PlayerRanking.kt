package com.korzhuck.foosball.models

data class PlayerRanking(
    val player: Player,
    val matchesCount: Int,
    val winsCount: Int,
)
