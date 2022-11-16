package com.korzhuck.foosball.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Player(
    val name: String,
): Parcelable
