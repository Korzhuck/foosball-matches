package com.korzhuck.foosball.models

data class MatchResult(
    val player1: Player,
    val score1: Int,
    val player2: Player,
    val score2: Int,
) {
    fun toRankings() = listOf(
        PlayerRanking(
            player = player1,
            matchesCount = 1,
            winsCount = if (isFirstWin()) 1 else 0,
        ),
        PlayerRanking(
            player = player2,
            matchesCount = 1,
            winsCount = if (isFirstWin()) 0 else 1,
        )
    )

    private fun isFirstWin() = score1 > score2
}
