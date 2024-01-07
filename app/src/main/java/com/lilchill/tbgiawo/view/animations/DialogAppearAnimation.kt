package com.lilchill.tbgiawo.view.animations

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.view.View
import com.lilchill.tbgiawo.view.layouts.GameStartDialogLayout

object DialogAppearAnimation {
    fun GameStartDialogLayout.animateAppearing(){
        firstPlayerText.translationY = -firstPlayerText.bottom.toFloat()
        firstPlayerPickerView.alpha = 0F
        firstPlayerPickerView.scaleX = 0F
        firstPlayerPickerView.scaleY = 0F
        firstPlayerColorText.translationX = -firstPlayerColorText.right.toFloat()
        secondPlayerColorText.translationX = secondPlayerColorText.left.toFloat()
        firstPlayerColorPickerView.alpha = 0F
        firstPlayerColorPickerView.scaleX = 0F
        firstPlayerColorPickerView.scaleY = 0F
        secondPlayerColorPickerView.alpha = 0F
        secondPlayerColorPickerView.scaleX = 0F
        secondPlayerColorPickerView.scaleY = 0F
        proceedButton.translationY = proceedButton.top.toFloat()
        AnimatorSet().apply {
            duration = 520
            playTogether(
                ObjectAnimator.ofFloat(firstPlayerText, View.TRANSLATION_Y, firstPlayerText.translationY, 0F),
                ObjectAnimator.ofFloat(firstPlayerColorText, View.TRANSLATION_X, firstPlayerColorText.translationX, 0F),
                ObjectAnimator.ofFloat(secondPlayerColorText, View.TRANSLATION_X, secondPlayerColorText.translationX, 0F),
                ObjectAnimator.ofFloat(proceedButton, View.TRANSLATION_Y, proceedButton.translationY, 0F),
                ValueAnimator.ofFloat(0F, 1F).apply {
                    this.addUpdateListener {
                        firstPlayerPickerView.alpha = it.animatedValue as Float
                        firstPlayerPickerView.scaleX = it.animatedValue as Float
                        firstPlayerPickerView.scaleY = it.animatedValue as Float
                        firstPlayerColorPickerView.alpha = it.animatedValue as Float
                        firstPlayerColorPickerView.scaleX = it.animatedValue as Float
                        firstPlayerColorPickerView.scaleY = it.animatedValue as Float
                        secondPlayerColorPickerView.alpha = it.animatedValue as Float
                        secondPlayerColorPickerView.scaleX = it.animatedValue as Float
                        secondPlayerColorPickerView.scaleY = it.animatedValue as Float
                    }
                }
            )
            start()
        }
    }
}