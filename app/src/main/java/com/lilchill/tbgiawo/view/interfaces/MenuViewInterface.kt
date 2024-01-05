package com.lilchill.tbgiawo.view.interfaces

interface MenuViewInterface {
    fun navigateToGame(locally : Boolean)
    fun navigateToSettings()
    fun updateTheNumberOfWins(number : Int)
    fun saveGameType(local : Boolean)
    fun finish()
}