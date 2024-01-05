package com.lilchill.tbgiawo.presenter

interface GamePresenter {
    fun onFieldClicked()
    fun onPlayerOneWin()
    fun onPlayerTwoWin()
    fun onTie()
    fun onLeftClicked()
    fun onRightClicked()
}