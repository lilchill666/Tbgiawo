package com.lilchill.tbgiawo.view.animations.game

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.os.Build
import android.util.Log
import android.view.View
import androidx.core.animation.doOnEnd
import androidx.core.animation.doOnStart
import androidx.lifecycle.lifecycleScope
import com.lilchill.tbgiawo.view.AppActivity
import com.lilchill.tbgiawo.view.animations.game.PointerAnimation.animateMove
import com.lilchill.tbgiawo.view.fragments.GameFragment
import com.lilchill.tbgiawo.view.layouts.views.GamePointer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

object PointerAnimation {
    private val needsDifferentAnimationTime = Build.MODEL == "POCO X4 Pro 5G"
    private var listOfAnimations = mutableListOf<Animator>()
    private var animation = AnimatorSet()
    private var checkingCoroutine : Job? = null
    val animationIsRunning get() = animation.isRunning
    var listOfPositions = listOf<Float>()
    fun GamePointer.animateMove(oldPosition : Int, newPosition : Int){
        listOfAnimations.add(
            AnimatorSet().apply {
                doOnEnd {
                    listOfAnimations.remove(this)
                }
                playSequentially(
                    ValueAnimator.ofFloat(0.3F, 0.1F).apply {
                        duration = if (needsDifferentAnimationTime) 80 else 140
                        addUpdateListener {
                            scaleX = it.animatedValue as Float
                            scaleY = it.animatedValue as Float
                        }
                    },
                    ObjectAnimator.ofFloat(this@animateMove, View.TRANSLATION_X, listOfPositions[oldPosition], listOfPositions[newPosition]).apply {
                        this.duration = if (needsDifferentAnimationTime) 130 else 240
                    },
                    ValueAnimator.ofFloat(0.1F, 0.3F).apply {
                        duration = if (needsDifferentAnimationTime) 80 else 140
                        addUpdateListener {
                            scaleX = it.animatedValue as Float
                            scaleY = it.animatedValue as Float
                        }
                    }
                )
            }
        )
        if (this@PointerAnimation.animation.isStarted){
            checkingCoroutine = ((context as AppActivity).supportFragmentManager.fragments.last() as GameFragment).lifecycleScope.launch(Dispatchers.IO) {
                do {
                    Unit
                } while (listOfPositions.find { it == this@animateMove.translationX } != null)
                withContext(Dispatchers.Main){
                    this@PointerAnimation.animation.cancel()
                    this@PointerAnimation.animation = AnimatorSet().apply {
                        playSequentially(listOfAnimations)
                        start()
                    }
                }
            }
        } else {
            this@PointerAnimation.animation = AnimatorSet().apply {
                playSequentially(listOfAnimations)
                start()
            }
        }
    }
    fun GamePointer.animateDrop(translationY : Float, endAction : () -> Unit){
        val bounceHeight = ((if (translationY == listOfPositions[4]) 4 else 16)..(if (translationY == listOfPositions[4]) 8 else 23)).random().toFloat()
        AnimatorSet().apply {
            playSequentially(
                ValueAnimator.ofFloat(0.3F, 0.15F).apply {
                    duration = 220
                    this.addUpdateListener {
                        this@animateDrop.scaleY = it.animatedValue as Float
                        this@animateDrop.scaleX = it.animatedValue as Float
                    }
                },
                ValueAnimator.ofFloat(0.15F, 0.9F).apply {
                    duration = 150
                    this.addUpdateListener {
                        this@animateDrop.scaleY = it.animatedValue as Float
                        this@animateDrop.scaleX = it.animatedValue as Float
                    }
                },
                ObjectAnimator.ofFloat(this@animateDrop, View.TRANSLATION_Y, 0F, translationY).apply {
                    duration = 620
                },
                ObjectAnimator.ofFloat(this@animateDrop, View.TRANSLATION_Y, translationY, translationY - resources.displayMetrics.density * bounceHeight).apply {
                    duration = 180
                },
                ObjectAnimator.ofFloat(this@animateDrop, View.TRANSLATION_Y, translationY - resources.displayMetrics.density * bounceHeight, translationY).apply {
                    duration = 160
                },
                ObjectAnimator.ofFloat(this@animateDrop, View.TRANSLATION_Y, translationY, translationY - resources.displayMetrics.density * bounceHeight / 2F).apply {
                    duration = 90
                },
                ObjectAnimator.ofFloat(this@animateDrop, View.TRANSLATION_Y, translationY - resources.displayMetrics.density * bounceHeight / 2F, translationY).apply {
                    duration = 80
                }
            )
            this.doOnEnd {
                endAction()
                AnimatorSet().apply {
                    this.duration = 270
                    this.playTogether(
                        ObjectAnimator.ofFloat(this@animateDrop, View.ALPHA, 0F, 1F),
                        ObjectAnimator.ofFloat(this@animateDrop, View.TRANSLATION_Y, this@animateDrop.translationY, 0F),
                        ValueAnimator.ofFloat(0F, 0.3F).apply {
                            this.addUpdateListener {
                                this@animateDrop.scaleY = it.animatedValue as Float
                                this@animateDrop.scaleX = it.animatedValue as Float
                            }
                        }
                    )
                    this.start()
                }
            }
            start()
        }
    }
}