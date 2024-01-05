package com.lilchill.tbgiawo.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.lilchill.tbgiawo.R
import com.lilchill.tbgiawo.constants.AppKeys
import com.lilchill.tbgiawo.presenter.implementations.MenuPresenterImpl
import com.lilchill.tbgiawo.view.AppActivity
import com.lilchill.tbgiawo.view.animations.MenuAppearAnimation
import com.lilchill.tbgiawo.view.animations.MenuAppearAnimation.animateAppearing
import com.lilchill.tbgiawo.view.interfaces.MenuViewInterface
import com.lilchill.tbgiawo.view.layouts.MenuLayout

class MenuFragment : Fragment(), MenuViewInterface {
    private val presenter = MenuPresenterImpl(this)
    private val layout by lazy { MenuLayout(requireActivity()) }

    override fun navigateToGame(locally: Boolean) {
        (requireActivity() as AppActivity).supportFragmentManager.beginTransaction().replace(R.id.root, GameFragment()).commit()
    }

    override fun navigateToSettings() {
        (requireActivity() as AppActivity).supportFragmentManager.beginTransaction().replace(R.id.root, SettingsFragment()).commit()
    }

    override fun updateTheNumberOfWins(number: Int) {
        layout.wins.text = resources.getString(R.string.winsMenuText, number)
    }

    override fun saveGameType(local: Boolean) {
        (requireActivity() as AppActivity).storage.encode(AppKeys.GAME_TYPE, if (local) "Local" else "Online")
    }

    override fun finish() {
        requireActivity().finish()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View = layout

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(
            object : OnBackPressedCallback(true){
                override fun handleOnBackPressed() {}
            }
        )
        layout.viewTreeObserver.addOnGlobalLayoutListener(
            object : ViewTreeObserver.OnGlobalLayoutListener{
                override fun onGlobalLayout() {
                    layout.viewTreeObserver.removeOnGlobalLayoutListener(this)
                    with(MenuAppearAnimation){
                        layout.animateAppearing()
                    }
                }
            }
        )
        presenter.onFragmentCreated()
        layout.startLocallyButton.setOnClickListener{
            presenter.onPlayLocallyButtonClicked()
        }
        layout.startOnlineButton.setOnClickListener{
            presenter.onPlayOnlineButtonClicked()
        }
        layout.settings.setOnClickListener{
            presenter.onSettingsButtonClicked()
        }
        layout.finishButton.setOnClickListener{
            presenter.onExitButtonClicked()
        }
    }
}