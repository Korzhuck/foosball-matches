package com.korzhuck.foosball.utils

sealed class State<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : State<T>(data)
    class Loading<T> : State<T>()
}
