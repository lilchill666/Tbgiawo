package com.lilchill.tbgiawo.model.game

import com.lilchill.tbgiawo.constants.AppColors
import com.lilchill.tbgiawo.model.data.MatchChecker

class LocalGameModel {
    val matchChecker = MatchChecker()
    var playerOneColor = AppColors.basePlayerOneColor
    var playerTwoColor = AppColors.basePlayerTwoColor
    var currentPlayer = -1
    var fieldCanBeClicked = true
}