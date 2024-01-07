package com.lilchill.tbgiawo.view.interfaces

interface GameViewInterface {
    fun dropPlayerOneElement(playerTwoColor : Int)
    fun dropPlayerTwoElement(playerOneColor : Int)
    fun movePointerLeft()
    fun movePointerRight()

    fun onTie()
    fun onPlayerOneWin()
    fun onPlayerTwoWin()
}