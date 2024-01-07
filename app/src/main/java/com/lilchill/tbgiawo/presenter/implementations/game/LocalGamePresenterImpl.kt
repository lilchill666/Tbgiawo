package com.lilchill.tbgiawo.presenter.implementations.game

import android.util.Log
import com.lilchill.tbgiawo.model.data.GameCellState
import com.lilchill.tbgiawo.model.game.LocalGameModel
import com.lilchill.tbgiawo.presenter.GamePresenter
import com.lilchill.tbgiawo.view.animations.game.PointerAnimation
import com.lilchill.tbgiawo.view.animations.game.PointerAnimation.animateDrop
import com.lilchill.tbgiawo.view.interfaces.GameViewInterface
import com.lilchill.tbgiawo.view.layouts.views.GameField
import com.lilchill.tbgiawo.view.layouts.views.GamePointer

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
        if (localGameModel.fieldCanBeClicked){
            fragment.movePointerLeft()
        }
    }

    override fun onRightClicked() {
        if (localGameModel.fieldCanBeClicked) {
            fragment.movePointerRight()
        }
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

    override fun dropElement(gameField : GameField, gamePointer: GamePointer, player : Int, endAction : () -> Unit) {
        with(PointerAnimation){
            val firstUntaken = gameField.cells[gamePointer.currentPosition].findLast { it.state == GameCellState.Empty}
            if (firstUntaken == null){
                onClickInterrupted()
                return@with
            }
            gamePointer.animateDrop(gamePointer.listOfVerticalPositions[gameField.cells[gamePointer.currentPosition].indexOf(firstUntaken)]){
                gamePointer.currentPosition = 3
                firstUntaken.imageView.setImageDrawable(gamePointer.drawable)
                firstUntaken.state = if (player == 1) GameCellState.Player else GameCellState.Opponent
                gamePointer.alpha = 0F
                gamePointer.scaleX = 0F
                gamePointer.scaleY = 0F
                gamePointer.translationX = 0F
                gamePointer.translationY = -(gamePointer.bottom.toFloat())
                gamePointer.updateDrawable(
                    if (player == 1){
                        localGameModel.playerTwoColor
                    } else {
                        localGameModel.playerOneColor
                    }
                )
                endAction()
            }
        }
    }
}