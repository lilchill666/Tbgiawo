package com.lilchill.tbgiawo.presenter.implementations.game

import com.lilchill.tbgiawo.presenter.GamePresenter
import com.lilchill.tbgiawo.view.interfaces.GameViewInterface

class OnlineGamePresenterImpl(
    private val fragment: GameViewInterface
) : GamePresenter {
    override fun onFieldClicked() {
        TODO("Not yet implemented")
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
}