package com.lilchill.tbgiawo.model.data

import com.lilchill.tbgiawo.model.data.entities.GameCell

class MatchChecker {
    fun checkForMatches(cells: List<List<GameCell>>): GameResult {
        val verticalMatch = checkVerticalMatches(cells)
        val horizontalMatch = checkHorizontalMatches(cells)
        val diagonalMatch = checkDiagonalMatches(cells)
        return when {
            verticalMatch != GameResult.None -> verticalMatch
            horizontalMatch != GameResult.None -> horizontalMatch
            diagonalMatch != GameResult.None -> diagonalMatch
            else -> GameResult.None
        }
    }

    private fun checkVerticalMatches(cells: List<List<GameCell>>): GameResult {
        for (column in 0 until 7) {
            for (row in 0 until 3) {
                val cell = cells[column][row]
                if (cell.state != GameCellState.Empty &&
                    cell.state == cells[column][row + 1].state &&
                    cell.state == cells[column][row + 2].state &&
                    cell.state == cells[column][row + 3].state
                ) {
                    return getResultForState(cell.state)
                }
            }
        }
        return GameResult.None
    }

    private fun checkHorizontalMatches(cells: List<List<GameCell>>): GameResult {
        for (column in 0 until 4) {
            for (row in 0 until 6) {
                val cell = cells[column][row]
                if (cell.state != GameCellState.Empty &&
                    cell.state == cells[column + 1][row].state &&
                    cell.state == cells[column + 2][row].state &&
                    cell.state == cells[column + 3][row].state
                ) {
                    return getResultForState(cell.state)
                }
            }
        }
        return GameResult.None
    }

    private fun checkDiagonalMatches(cells: List<List<GameCell>>): GameResult {
        for (column in 0 until 4) {
            for (row in 0 until 3) {
                val cell = cells[column][row]
                if (cell.state != GameCellState.Empty &&
                    cell.state == cells[column + 1][row + 1].state &&
                    cell.state == cells[column + 2][row + 2].state &&
                    cell.state == cells[column + 3][row + 3].state
                ) {
                    return getResultForState(cell.state)
                }
            }
        }
        for (column in 3 until 7) {
            for (row in 0 until 3) {
                val cell = cells[column][row]
                if (cell.state != GameCellState.Empty &&
                    cell.state == cells[column - 1][row + 1].state &&
                    cell.state == cells[column - 2][row + 2].state &&
                    cell.state == cells[column - 3][row + 3].state
                ) {
                    return getResultForState(cell.state)
                }
            }
        }
        return GameResult.None
    }

    private fun getResultForState(state: GameCellState): GameResult {
        return when (state) {
            is GameCellState.Player -> GameResult.Player
            is GameCellState.Opponent -> GameResult.Opponent
            else -> GameResult.None
        }
    }
}

