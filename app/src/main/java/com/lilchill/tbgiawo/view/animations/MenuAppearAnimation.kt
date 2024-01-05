package com.lilchill.tbgiawo.view.animations

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.util.Log
import android.view.View
import com.lilchill.tbgiawo.view.layouts.MenuLayout
import java.util.Random

object MenuAppearAnimation {
    fun MenuLayout.animateAppearing(){
        startOnlineButton.translationX = if (Random().nextBoolean()) startLocallyButton.right.toFloat() else (-startLocallyButton.right).toFloat()
        startLocallyButton.translationY = -startLocallyButton.bottom.toFloat()
        finishButton.translationY = finishButton.top.toFloat()
        wins.alpha = 0F
        wins.scaleX = 0F
        wins.scaleY = 0F
        AnimatorSet().apply {
            playSequentially(
                AnimatorSet().apply {
                    duration = 520
                    playTogether(
                        ObjectAnimator.ofFloat(startOnlineButton, View.TRANSLATION_X, startOnlineButton.translationX, 0F),
                        ObjectAnimator.ofFloat(startLocallyButton, View.TRANSLATION_Y, startLocallyButton.translationY, 0F),
                        ObjectAnimator.ofFloat(finishButton, View.TRANSLATION_Y, finishButton.translationY, 0F),
                        ObjectAnimator.ofFloat(startOnlineButton, View.ALPHA, 0F, 1F),
                        ObjectAnimator.ofFloat(startLocallyButton, View.ALPHA, 0F, 1F),
                        ObjectAnimator.ofFloat(finishButton, View.ALPHA, 0F, 1F)
                    )
                },
                ValueAnimator.ofFloat(0F, 1F).apply {
                    duration = 380
                    addUpdateListener {
                        wins.scaleY = it.animatedValue as Float
                        wins.scaleX = it.animatedValue as Float
                        wins.alpha = it.animatedValue as Float
                    }
                }
            )
            start()
        }
    }
}