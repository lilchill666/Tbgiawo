package com.lilchill.tbgiawo.model.data

sealed class GameResult {
    data object Player : GameResult()
    data object Opponent : GameResult()
    data object None : GameResult()
}