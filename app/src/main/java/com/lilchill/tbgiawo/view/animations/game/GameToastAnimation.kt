package com.lilchill.tbgiawo.view.animations.game

import android.animation.ObjectAnimator
import android.view.View
import androidx.core.animation.doOnEnd
import androidx.core.animation.doOnStart
import com.lilchill.tbgiawo.R
import com.lilchill.tbgiawo.model.data.GameResult
import com.lilchill.tbgiawo.view.animations.game.GameToastAnimation.animateAppearing
import com.lilchill.tbgiawo.view.layouts.views.GameToast

object GameToastAnimation {
    fun GameToast.animateAppearing(player : GameResult, endAction : () -> Unit){
        val tr = translationY - resources.displayMetrics.density * 40F
        text = if (player == GameResult.None) {
            resources.getString(R.string.toastTie)
        } else {
            resources.getString(R.string.toastText, if (player == GameResult.Player) 1 else 2)
        }
        ObjectAnimator.ofFloat(this, View.TRANSLATION_Y, tr, 0F).apply {
            duration = 340
            this.doOnEnd {
                ObjectAnimator.ofFloat(this@animateAppearing, View.TRANSLATION_Y, 0F, tr).apply{
                    duration = 340
                    startDelay = 1000
                    this.doOnStart {
                        endAction()
                    }
                    start()
                }
            }
            start()
        }
    }
}