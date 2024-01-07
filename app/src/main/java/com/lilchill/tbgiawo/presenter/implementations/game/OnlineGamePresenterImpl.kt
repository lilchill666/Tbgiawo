package com.lilchill.tbgiawo.presenter.implementations.game

import com.lilchill.tbgiawo.model.data.GameResult
import com.lilchill.tbgiawo.presenter.GamePresenter
import com.lilchill.tbgiawo.view.interfaces.GameViewInterface
import com.lilchill.tbgiawo.view.layouts.GameLayout
import com.lilchill.tbgiawo.view.layouts.views.GameField
import com.lilchill.tbgiawo.view.layouts.views.GamePointer

class OnlineGamePresenterImpl(
    private val fragment: GameViewInterface
) : GamePresenter {
    override fun onFieldClicked() {
        TODO("Not yet implemented")
    }
    override fun onLeftClicked() {
        fragment.movePointerLeft()
    }

    override fun onRightClicked() {
        fragment.movePointerRight()
    }

    override fun resetField(layout: GameLayout, gameResult: GameResult) {
        TODO("Not yet implemented")
    }

    override fun onClickCompleted(gameField: GameField) {
        TODO("Not yet implemented")
    }

    override fun onClickInterrupted() {
        TODO("Not yet implemented")
    }

    override fun getFirstColor(): Int {
        TODO("Not yet implemented")
    }

    override fun setFirstPlayerAndColors(
        firstPlayer: Int,
        color1: Int,
        color2: Int,
        dismissAction: () -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override fun dropElement(
        gameField: GameField,
        gamePointer: GamePointer,
        player: Int,
        endAction: () -> Unit
    ) {
        TODO("Not yet implemented")
    }
}