package com.lilchill.tbgiawo.presenter

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
}