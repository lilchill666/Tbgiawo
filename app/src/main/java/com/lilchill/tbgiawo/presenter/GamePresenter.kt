package com.lilchill.tbgiawo.presenter

import com.lilchill.tbgiawo.view.layouts.views.GameField
import com.lilchill.tbgiawo.view.layouts.views.GamePointer

interface GamePresenter {
    fun onFieldClicked()
    fun onPlayerOneWin()
    fun onPlayerTwoWin()
    fun onTie()
    fun onLeftClicked()
    fun onRightClicked()
    fun onClickCompleted()
    fun onClickInterrupted()
    fun getFirstColor() : Int
    fun setFirstPlayerAndColors(firstPlayer : Int, color1 : Int, color2 : Int, dismissAction : () -> Unit)
    fun dropElement(gameField : GameField, gamePointer: GamePointer, player : Int, endAction : () -> Unit)
}