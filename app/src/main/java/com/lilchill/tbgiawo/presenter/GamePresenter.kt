package com.lilchill.tbgiawo.presenter

interface GamePresenter {
    fun onPlayerOneMove()
    fun onPlayerTwoMove()
    fun onPlayerOneWin()
    fun onPlayerTwoWin()
    fun onTie()
}