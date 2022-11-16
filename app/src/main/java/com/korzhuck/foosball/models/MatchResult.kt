package com.korzhuck.foosball.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MatchResult(
    val id: Int,
    val player1: Player,
    val score1: Int,
    val player2: Player,
    val score2: Int,
) : Parcelable {
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

    override fun equals(other: Any?): Boolean {
        return other is MatchResult && other.id == id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}
