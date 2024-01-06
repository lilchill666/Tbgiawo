package com.lilchill.tbgiawo.presenter.implementations.game

import android.util.Log
import com.lilchill.tbgiawo.model.game.LocalGameModel
import com.lilchill.tbgiawo.presenter.GamePresenter
import com.lilchill.tbgiawo.view.interfaces.GameViewInterface

class LocalGamePresenterImpl(
    private val fragment: GameViewInterface
) : GamePresenter {
    private val localGameModel = LocalGameModel()
    override fun onFieldClicked() {
        if (!localGameModel.fieldCanBeClicked) return
        when (localGameModel.currentPlayer){
            -1 -> return
            1 -> fragment.dropPlayerOneElement(localGameModel.playerTwoColor).also { localGameModel.fieldCanBeClicked = false }
            2 -> fragment.dropPlayerTwoElement(localGameModel.playerOneColor).also { localGameModel.fieldCanBeClicked = false }
        }
    }

    override fun onPlayerOneWin() {
        TODO("Not yet implemented")
    }

    override fun onPlayerTwoWin() {
        TODO("Not yet implemented")
    }

    override fun onTie() {
        TODO("Not yet implemented")
    }

    override fun onLeftClicked() {
        fragment.movePointerLeft()
    }

    override fun onRightClicked() {
        fragment.movePointerRight()
    }

    override fun onClickCompleted() {
        //match check
        localGameModel.fieldCanBeClicked = true
        localGameModel.currentPlayer = if (localGameModel.currentPlayer == 1) 2 else 1
    }

    override fun onClickInterrupted() {
        localGameModel.fieldCanBeClicked = true
    }

    override fun getFirstColor(): Int = if (localGameModel.currentPlayer == 1) localGameModel.playerOneColor else localGameModel.playerTwoColor

    override fun setFirstPlayerAndColors(
        firstPlayer: Int,
        color1: Int,
        color2: Int,
        dismissAction: () -> Unit
    ) {
        localGameModel.playerOneColor = color1
        localGameModel.playerTwoColor = color2
        localGameModel.currentPlayer = firstPlayer
        dismissAction()
    }
}