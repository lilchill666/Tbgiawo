package com.lilchill.tbgiawo.view.layouts.views

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.GradientDrawable
import android.util.Log
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.AppCompatImageView
import androidx.lifecycle.lifecycleScope
import com.lilchill.tbgiawo.R
import com.lilchill.tbgiawo.model.data.GameCellState
import com.lilchill.tbgiawo.model.data.entities.GameCell
import com.lilchill.tbgiawo.presenter.GamePresenter
import com.lilchill.tbgiawo.view.AppActivity
import com.lilchill.tbgiawo.view.animations.game.PointerAnimation
import com.lilchill.tbgiawo.view.fragments.GameFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GamePointer(context: Context) : AppCompatImageView(context) {
    var currentPosition = 3
    private var distance = 0F
    var listOfVerticalPositions = listOf<Float>()
    constructor(context: Context, d : Float) : this(context){
        PointerAnimation.listOfPositions = listOf(d * -3, d * -2, -d, 0F, d, d * 2, d * 3)
        listOfVerticalPositions = listOf(d, d * 2, d * 3, d * 4, d * 5, d * 6)
        distance = d
    }
    fun moveLeft(){
        if (currentPosition != 0){
            with(PointerAnimation){
                animateMove(currentPosition, currentPosition - 1)
            }
            currentPosition--
        }
    }
    fun moveRight(){
        if (currentPosition != 6){
            with(PointerAnimation){
                animateMove(currentPosition, currentPosition + 1)
            }
            currentPosition++
        }
    }
    fun dropElement(presenter: GamePresenter, player : Int, gameField: GameField, endAction: () -> Unit){
        ((context as AppActivity).supportFragmentManager.fragments.last() as GameFragment).lifecycleScope.launch(Dispatchers.IO) {
            do {
               Unit
            } while (PointerAnimation.animationIsRunning)
            withContext(Dispatchers.Main){
                presenter.dropElement(gameField, this@GamePointer, player, endAction)
            }
        }
    }
    fun updateDrawable(withColor : Int){
        setImageDrawable(
            GradientDrawable().apply {
                this.cornerRadius = 255F
                this.color = ColorStateList.valueOf(withColor)
            }
        )
    }
}