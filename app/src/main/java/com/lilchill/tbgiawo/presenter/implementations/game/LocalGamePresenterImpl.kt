package com.lilchill.tbgiawo.presenter.implementations.game

import android.util.Log
import com.lilchill.tbgiawo.presenter.GamePresenter
import com.lilchill.tbgiawo.view.interfaces.GameViewInterface

class LocalGamePresenterImpl(
    private val fragment: GameViewInterface
) : GamePresenter {
    override fun onFieldClicked() {
        Log.d("LILCHILL", "Field is clicked")
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

}