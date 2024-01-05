package com.lilchill.tbgiawo.view.layouts.views

import android.content.Context
import android.graphics.Rect
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.AppCompatImageView
import com.lilchill.tbgiawo.R
import com.lilchill.tbgiawo.model.data.entities.GameCell
import com.lilchill.tbgiawo.view.animations.game.PointerAnimation

class GamePointer(context: Context) : AppCompatImageView(context) {
    private var currentPosition = 3
    private var distance = 0F
    constructor(context: Context, d : Float) : this(context){
        PointerAnimation.listOfPositions = listOf(d * -3, d * -2, -d, 0F, d, d * 2, d * 3)
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
}