package com.lilchill.tbgiawo.model.data

sealed class GameCellState {
    data object Empty : GameCellState()
    data object Player : GameCellState()
    data object Opponent : GameCellState()
}