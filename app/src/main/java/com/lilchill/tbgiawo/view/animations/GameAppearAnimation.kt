package com.lilchill.tbgiawo.view.animations

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.os.Build
import android.view.View
import com.google.android.material.card.MaterialCardView
import com.lilchill.tbgiawo.view.layouts.GameLayout
import java.util.Random

object GameAppearAnimation {
    fun GameLayout.animateAppearing(){
        val view = gameField.parent as MaterialCardView
        AnimatorSet().apply {
            duration = 580
            playTogether(
                ObjectAnimator.ofFloat(view, if (view.translationX == 0F) View.TRANSLATION_Y else View.TRANSLATION_X, if (view.translationX == 0F) view.translationY else view.translationX, 0F),
                ValueAnimator.ofFloat(0F, 1F).apply {
                    addUpdateListener {
                        view.scaleY = it.animatedValue as Float
                        view.scaleX = it.animatedValue as Float
                        view.alpha = it.animatedValue as Float
                    }
                },
                ObjectAnimator.ofFloat(pointer, if (pointer.translationX == 0F) View.TRANSLATION_Y else View.TRANSLATION_X, if (pointer.translationX == 0F) pointer.translationY else pointer.translationX, 0F),
                ValueAnimator.ofFloat(0F, 0.3F).apply {
                    addUpdateListener {
                        pointer.scaleY = it.animatedValue as Float
                        pointer.scaleX = it.animatedValue as Float
                    }
                },
                ObjectAnimator.ofFloat(pointer, View.ALPHA, 0F, 1F)
            )
            start()
        }
    }
}