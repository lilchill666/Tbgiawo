package com.lilchill.tbgiawo.view.fragments

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
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
import com.google.android.material.card.MaterialCardView
import com.lilchill.tbgiawo.R
import com.lilchill.tbgiawo.constants.AppKeys
import com.lilchill.tbgiawo.presenter.implementations.game.LocalGamePresenterImpl
import com.lilchill.tbgiawo.presenter.implementations.game.OnlineGamePresenterImpl
import com.lilchill.tbgiawo.view.AppActivity
import com.lilchill.tbgiawo.view.animations.GameAppearAnimation
import com.lilchill.tbgiawo.view.interfaces.GameViewInterface
import com.lilchill.tbgiawo.view.layouts.GameLayout
import com.lilchill.tbgiawo.view.layouts.GameStartDialogLayout
import nl.dionsegijn.konfetti.core.Party
import nl.dionsegijn.konfetti.core.Position
import nl.dionsegijn.konfetti.core.emitter.Emitter
import java.lang.Exception
import java.util.Random
import java.util.concurrent.TimeUnit

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
                    val v = layout.gameField.parent as MaterialCardView
                    if ((0..1000).random() % 2 == 0){
                        v.translationY = if ((0..1000).random() % 2 == 0){
                            v.bottom.toFloat()
                        } else {
                            -v.bottom.toFloat()
                        }
                        layout.pointer.translationX = if (Random().nextBoolean()){
                            (v.resources.displayMetrics.widthPixels / 2 + layout.pointer.width / 2).toFloat()
                        } else {
                            -(v.resources.displayMetrics.widthPixels / 2 + layout.pointer.width / 2).toFloat()
                        }
                    } else {
                        v.translationX = if ((0..1000).random() % 2 == 0){
                            v.right.toFloat()
                        } else {
                            -v.right.toFloat()
                        }
                        layout.pointer.translationY = if (Random().nextBoolean()){
                            (v.resources.displayMetrics.heightPixels / 2 + layout.pointer.height / 2).toFloat()
                        } else {
                            -(v.resources.displayMetrics.heightPixels / 2 + layout.pointer.height / 2).toFloat()
                        }
                    }
                    layout.pointer.alpha = 0F
                    layout.pointer.scaleX = 0F
                    layout.pointer.scaleY = 0F
                    v.alpha = 0F
                    v.scaleX = 0F
                    v.scaleY = 0F
                    if (local){
                        val dialog = Dialog(requireContext())
                        val dialogView = GameStartDialogLayout(requireContext())
                        dialog.setCanceledOnTouchOutside(false)
                        dialog.setContentView(dialogView)
                        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                        dialog.setOnDismissListener {
                            presenter.setFirstPlayerAndColors(
                                when (dialogView.chosenFirstPlayer.value){
                                    0 -> 1
                                    1 -> 2
                                    2 -> (1..2).random()
                                    else -> (1..2).random()
                                },
                                dialogView.playerOneColor.value ?: Color.RED,
                                dialogView.playerTwoColor.value ?: Color.BLACK
                            ) {
                                layout.pointer.updateDrawable(presenter.getFirstColor())
                                with(GameAppearAnimation){
                                    layout.animateAppearing()
                                }
                            }
                            dialogView.playerOneColor.removeObservers(this@GameFragment)
                            dialogView.playerTwoColor.removeObservers(this@GameFragment)
                            dialogView.chosenFirstPlayer.removeObservers(this@GameFragment)
                        }
                        dialogView.proceedButton.setOnClickListener {
                            dialog.cancel()
                        }
                        dialog.show()
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

    override fun dropPlayerOneElement(playerTwoColor : Int) {
        layout.pointer.dropElement(1, playerTwoColor, layout.gameField, {presenter.onClickInterrupted()}) {
            presenter.onClickCompleted()
        }
    }

    override fun dropPlayerTwoElement(playerOneColor : Int) {
        layout.pointer.dropElement(2, playerOneColor, layout.gameField, {presenter.onClickInterrupted()}) {
            presenter.onClickCompleted()
        }
    }

    override fun movePointerLeft() {
        layout.pointer.moveLeft()
    }

    override fun movePointerRight() {
        layout.pointer.moveRight()
    }
}