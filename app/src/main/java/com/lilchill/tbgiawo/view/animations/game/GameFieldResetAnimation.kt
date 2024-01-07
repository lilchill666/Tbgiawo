package com.lilchill.tbgiawo.view.animations.game


import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.view.View
import androidx.core.animation.doOnEnd
import com.lilchill.tbgiawo.model.data.GameCellState
import com.lilchill.tbgiawo.view.AppActivity
import com.lilchill.tbgiawo.view.layouts.views.GameField

object GameFieldResetAnimation {
    fun GameField.resetTheField(endAction : () -> Unit){
        val list = mutableListOf<Animator>()
        cells.forEach {
            it.forEach {cell ->
                cell.state = GameCellState.Empty
                list.add(
                    AnimatorSet().apply {
                        playTogether(
                            ObjectAnimator.ofFloat(cell.imageView, View.TRANSLATION_Y, 0F, -cell.imageView.bottom.toFloat()),
                            ObjectAnimator.ofFloat(cell.imageView, View.ALPHA, 1F, 0F)
                        )
                    }
                )
            }
        }
        AnimatorSet().apply {
            duration = 620
            doOnEnd {
                endAction()
            }
            playTogether(list)
            start()
        }
    }
}