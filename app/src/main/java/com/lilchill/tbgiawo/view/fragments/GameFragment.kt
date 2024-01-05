package com.lilchill.tbgiawo.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.lilchill.tbgiawo.R
import com.lilchill.tbgiawo.constants.AppKeys
import com.lilchill.tbgiawo.presenter.implementations.game.LocalGamePresenterImpl
import com.lilchill.tbgiawo.presenter.implementations.game.OnlineGamePresenterImpl
import com.lilchill.tbgiawo.view.AppActivity
import com.lilchill.tbgiawo.view.interfaces.GameViewInterface
import com.lilchill.tbgiawo.view.layouts.GameLayout

class GameFragment : Fragment(), GameViewInterface {
    private val presenter by lazy {
        if ((requireActivity() as AppActivity).storage.decodeString(AppKeys.GAME_TYPE, "Local") == "Local"){
            LocalGamePresenterImpl(this)
        } else {
            OnlineGamePresenterImpl(this)
        }
    }
    private val layout by lazy { GameLayout(requireActivity()) }
    private val local by lazy { (requireActivity() as AppActivity).storage.decodeString(AppKeys.GAME_TYPE, "Local") == "Local" }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View = layout

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(
            object : OnBackPressedCallback(true){
                override fun handleOnBackPressed() {
                    if (local){
                        (requireActivity() as AppActivity).supportFragmentManager.beginTransaction().replace(R.id.root, MenuFragment()).commit()
                    }
                }
            }
        )
    }

    override fun dropPlayerOneElement() {

    }

    override fun dropPlayerTwoElement() {
        TODO("Not yet implemented")
    }
}