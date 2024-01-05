package com.lilchill.tbgiawo.presenter.implementations

import com.lilchill.tbgiawo.model.MenuModel
import com.lilchill.tbgiawo.presenter.MenuPresenter
import com.lilchill.tbgiawo.view.interfaces.MenuViewInterface

class MenuPresenterImpl(
    private val fragment: MenuViewInterface,
) : MenuPresenter {
    private val model = MenuModel()

    override fun onFragmentCreated() {
        fragment.updateTheNumberOfWins(model.getNumberOfWins())
    }

    override fun onPlayLocallyButtonClicked() {
        fragment.saveGameType(true)
        fragment.navigateToGame(true)
    }

    override fun onPlayOnlineButtonClicked() {
        fragment.saveGameType(false)
        fragment.navigateToGame(false)
    }

    override fun onExitButtonClicked() {
        fragment.finish()
    }
}