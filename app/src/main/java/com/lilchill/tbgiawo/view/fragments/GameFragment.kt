package com.lilchill.tbgiawo.view.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import com.lilchill.tbgiawo.R
import com.lilchill.tbgiawo.constants.AppKeys
import com.lilchill.tbgiawo.presenter.implementations.game.LocalGamePresenterImpl
import com.lilchill.tbgiawo.presenter.implementations.game.OnlineGamePresenterImpl
import com.lilchill.tbgiawo.view.AppActivity
import com.lilchill.tbgiawo.view.animations.GameAppearAnimation
import com.lilchill.tbgiawo.view.animations.MenuAppearAnimation
import com.lilchill.tbgiawo.view.animations.MenuAppearAnimation.animateAppearing
import com.lilchill.tbgiawo.view.animations.game.PointerAnimation
import com.lilchill.tbgiawo.view.interfaces.GameViewInterface
import com.lilchill.tbgiawo.view.layouts.GameLayout
import java.lang.Exception

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

    @SuppressLint("ClickableViewAccessibility")
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
        layout.viewTreeObserver.addOnGlobalLayoutListener(
            object : ViewTreeObserver.OnGlobalLayoutListener{
                override fun onGlobalLayout() {
                    layout.viewTreeObserver.removeOnGlobalLayoutListener(this)
                    with(GameAppearAnimation){
                        layout.animateAppearing()
                    }
                }
            }
        )
        layout.gameField.setOnClickListener {
            presenter.onFieldClicked()
        }
        layout.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_UP){
                if (event.x !in layout.fieldRect.left.toFloat()..layout.fieldRect.right.toFloat()){
                    if (lifecycle.currentState == Lifecycle.State.RESUMED){
                        try {
                            if (event.x in 0F..resources.displayMetrics.widthPixels.toFloat() / 2){
                                presenter.onLeftClicked()
                            } else if (event.x in resources.displayMetrics.widthPixels.toFloat() / 2..resources.displayMetrics.widthPixels.toFloat()) {
                                presenter.onRightClicked()
                            }
                        } catch (e : Exception){
                            e.printStackTrace()
                        }
                    }
                }
            }
            return@setOnTouchListener true
        }
    }

    override fun dropPlayerOneElement() {

    }

    override fun dropPlayerTwoElement() {
        TODO("Not yet implemented")
    }

    override fun movePointerLeft() {
        layout.pointer.moveLeft()
    }

    override fun movePointerRight() {
        layout.pointer.moveRight()
    }
}