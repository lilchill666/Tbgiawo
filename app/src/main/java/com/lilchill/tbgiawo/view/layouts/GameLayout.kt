package com.lilchill.tbgiawo.view.layouts

import android.content.Context
import android.graphics.Rect
import android.view.Gravity
import android.view.ViewTreeObserver
import android.widget.FrameLayout
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.get
import com.google.android.material.card.MaterialCardView
import com.lilchill.tbgiawo.R
import com.lilchill.tbgiawo.constants.AppColors
import com.lilchill.tbgiawo.view.layouts.views.GameField
import com.lilchill.tbgiawo.view.layouts.views.GamePointer
import com.lilchill.tbgiawo.view.layouts.views.GameToast
import nl.dionsegijn.konfetti.core.Party
import nl.dionsegijn.konfetti.core.Position
import nl.dionsegijn.konfetti.core.emitter.Emitter
import nl.dionsegijn.konfetti.xml.KonfettiView
import java.util.concurrent.TimeUnit
import kotlin.random.Random

class GameLayout(context: Context) : FrameLayout(context) {
    private val fieldSize = resources.displayMetrics.heightPixels * 73 / 100
    private val confetti = KonfettiView(context)
    private val helpFieldRect = Rect()
    val gameField = GameField(context, fieldSize)
    val pointer = GamePointer(context, gameField.distance)
    val toast = GameToast(context)
    val fieldRect : Rect get() {
        this[0].getGlobalVisibleRect(helpFieldRect)
        return helpFieldRect
    }
    init {
        background = AppCompatResources.getDrawable(context, R.drawable.background)
        addView(
            MaterialCardView(context).apply materialCard@ {
                addView(
                    gameField,
                    LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
                )
                radius = context.resources.displayMetrics.density * 22F
                strokeColor = AppColors.gameFieldStrokeColor
                strokeWidth = 7
                setCardBackgroundColor(AppColors.transparent)
                viewTreeObserver.addOnGlobalLayoutListener(
                    object : ViewTreeObserver.OnGlobalLayoutListener {
                        override fun onGlobalLayout() {
                            viewTreeObserver.removeOnGlobalLayoutListener(this)
                            this@GameLayout.addView(
                                pointer.apply {
                                    elevation = -1F
                                    scaleX = 0.3F
                                    scaleY = 0.3F
                                },
                                LayoutParams(
                                    gameField.cells[3][0].imageView.width,
                                    gameField.cells[3][0].imageView.height
                                ).apply {
                                    gravity = Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL
                                    this.setMargins(0, 0, 0, (resources.displayMetrics.density * 17F).toInt().plus(this@materialCard.measuredHeight))
                                }
                            )
                        }
                    }
                )
            },
            LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT).apply {
                this.gravity = Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL
                this.setMargins(0, 0, 0, (resources.displayMetrics.density * 20F).toInt())
            }
        )
        addView(
            confetti,
            LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        )
        addView(
            toast,
            LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT).apply {
                this.gravity = Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL
            }
        )
    }
    fun playRandomConfetti(){
        when (Random.nextBoolean()){
            true -> confetti.start(
                when ((0..4).random()){
                    0 -> Party(
                        angle = 90,
                        spread = (270..360).random(),
                        speed = 0F,
                        maxSpeed = 40F,
                        damping = 0.9F,
                        colors = listOf(AppColors.confetti1, AppColors.confetti2, AppColors.confetti3, AppColors.confetti4),
                        fadeOutEnabled = true,
                        position = Position.Relative(0.5, 1.0),
                        emitter = Emitter(duration = (550..750).random().toLong(), TimeUnit.MILLISECONDS).max(500)
                    )
                    1 -> Party(
                        angle = 90,
                        spread = (270..360).random(),
                        speed = 0F,
                        maxSpeed = 40F,
                        damping = 0.9F,
                        colors = listOf(AppColors.confetti1, AppColors.confetti2, AppColors.confetti3, AppColors.confetti4),
                        fadeOutEnabled = true,
                        position = Position.Relative(0.5, 0.0),
                        emitter = Emitter(duration = (550..750).random().toLong(), TimeUnit.MILLISECONDS).max(500)
                    )
                    2 -> Party(
                        angle = 180,
                        spread = (270..360).random(),
                        speed = 0F,
                        maxSpeed = 40F,
                        damping = 0.9F,
                        colors = listOf(AppColors.confetti1, AppColors.confetti2, AppColors.confetti3, AppColors.confetti4),
                        fadeOutEnabled = true,
                        position = Position.Relative(0.0, 0.5),
                        emitter = Emitter(duration = (550..750).random().toLong(), TimeUnit.MILLISECONDS).max(500)
                    )
                    3 -> Party(
                        angle = 0,
                        spread = (270..360).random(),
                        speed = 0F,
                        maxSpeed = 40F,
                        damping = 0.9F,
                        colors = listOf(AppColors.confetti1, AppColors.confetti2, AppColors.confetti3, AppColors.confetti4),
                        fadeOutEnabled = true,
                        position = Position.Relative(1.0, 0.5),
                        emitter = Emitter(duration = (550..750).random().toLong(), TimeUnit.MILLISECONDS).max(500)
                    )
                    else -> {
                        Party(
                            angle = 360,
                            spread = 360,
                            speed = 0F,
                            maxSpeed = 40F,
                            damping = 0.9F,
                            colors = listOf(AppColors.confetti1, AppColors.confetti2, AppColors.confetti3, AppColors.confetti4),
                            fadeOutEnabled = true,
                            position = Position.Relative(0.5, 0.5),
                            emitter = Emitter(duration = (550..750).random().toLong(), TimeUnit.MILLISECONDS).max(500)
                        )
                    }
                }
            )
            false -> confetti.start(
                when ((0..3).random()){
                    0 -> listOf(
                        Party(
                            angle = 90,
                            spread = (270..360).random(),
                            speed = 0F,
                            maxSpeed = 40F,
                            damping = 0.9F,
                            colors = listOf(AppColors.confetti1, AppColors.confetti2, AppColors.confetti3, AppColors.confetti4),
                            fadeOutEnabled = true,
                            position = Position.Relative(0.5, 1.0),
                            emitter = Emitter(duration = (550..750).random().toLong(), TimeUnit.MILLISECONDS).max(500)
                        ),
                        Party(
                            angle = 90,
                            spread = (270..360).random(),
                            speed = 0F,
                            maxSpeed = 40F,
                            damping = 0.9F,
                            colors = listOf(AppColors.confetti1, AppColors.confetti2, AppColors.confetti3, AppColors.confetti4),
                            fadeOutEnabled = true,
                            position = Position.Relative(0.5, 0.0),
                            emitter = Emitter(duration = (550..750).random().toLong(), TimeUnit.MILLISECONDS).max(500)
                        )
                    )
                    1 -> listOf(
                        Party(
                            angle = 180,
                            spread = (270..360).random(),
                            speed = 0F,
                            maxSpeed = 40F,
                            damping = 0.9F,
                            colors = listOf(AppColors.confetti1, AppColors.confetti2, AppColors.confetti3, AppColors.confetti4),
                            fadeOutEnabled = true,
                            position = Position.Relative(0.0, 0.5),
                            emitter = Emitter(duration = (550..750).random().toLong(), TimeUnit.MILLISECONDS).max(500)
                        ),
                        Party(
                            angle = 0,
                            spread = (270..360).random(),
                            speed = 0F,
                            maxSpeed = 40F,
                            damping = 0.9F,
                            colors = listOf(AppColors.confetti1, AppColors.confetti2, AppColors.confetti3, AppColors.confetti4),
                            fadeOutEnabled = true,
                            position = Position.Relative(1.0, 0.5),
                            emitter = Emitter(duration = (550..750).random().toLong(), TimeUnit.MILLISECONDS).max(500)
                        )
                    )
                    2 -> listOf(
                        Party(
                            angle = 180,
                            spread = (270..360).random(),
                            speed = 0F,
                            maxSpeed = 40F,
                            damping = 0.9F,
                            colors = listOf(AppColors.confetti1, AppColors.confetti2, AppColors.confetti3, AppColors.confetti4),
                            fadeOutEnabled = true,
                            position = Position.Relative(0.0, 0.5),
                            emitter = Emitter(duration = (550..750).random().toLong(), TimeUnit.MILLISECONDS).max(500)
                        ),
                        Party(
                            angle = 0,
                            spread = (270..360).random(),
                            speed = 0F,
                            maxSpeed = 40F,
                            damping = 0.9F,
                            colors = listOf(AppColors.confetti1, AppColors.confetti2, AppColors.confetti3, AppColors.confetti4),
                            fadeOutEnabled = true,
                            position = Position.Relative(1.0, 0.5),
                            emitter = Emitter(duration = (550..750).random().toLong(), TimeUnit.MILLISECONDS).max(500)
                        ),
                        Party(
                            angle = 90,
                            spread = (270..360).random(),
                            speed = 0F,
                            maxSpeed = 40F,
                            damping = 0.9F,
                            colors = listOf(AppColors.confetti1, AppColors.confetti2, AppColors.confetti3, AppColors.confetti4),
                            fadeOutEnabled = true,
                            position = Position.Relative(0.5, 1.0),
                            emitter = Emitter(duration = (550..750).random().toLong(), TimeUnit.MILLISECONDS).max(500)
                        ),
                        Party(
                            angle = 90,
                            spread = (270..360).random(),
                            speed = 0F,
                            maxSpeed = 40F,
                            damping = 0.9F,
                            colors = listOf(AppColors.confetti1, AppColors.confetti2, AppColors.confetti3, AppColors.confetti4),
                            fadeOutEnabled = true,
                            position = Position.Relative(0.5, 0.0),
                            emitter = Emitter(duration = (550..750).random().toLong(), TimeUnit.MILLISECONDS).max(500)
                        )
                    )
                    else -> listOf(
                        Party(
                            angle = 180,
                            spread = (270..360).random(),
                            speed = 0F,
                            maxSpeed = 40F,
                            damping = 0.9F,
                            colors = listOf(AppColors.confetti1, AppColors.confetti2, AppColors.confetti3, AppColors.confetti4),
                            fadeOutEnabled = true,
                            position = Position.Relative(0.0, 0.5),
                            emitter = Emitter(duration = (550..750).random().toLong(), TimeUnit.MILLISECONDS).max(500)
                        ),
                        Party(
                            angle = 0,
                            spread = (270..360).random(),
                            speed = 0F,
                            maxSpeed = 40F,
                            damping = 0.9F,
                            colors = listOf(AppColors.confetti1, AppColors.confetti2, AppColors.confetti3, AppColors.confetti4),
                            fadeOutEnabled = true,
                            position = Position.Relative(1.0, 0.5),
                            emitter = Emitter(duration = (550..750).random().toLong(), TimeUnit.MILLISECONDS).max(500)
                        ),
                        Party(
                            angle = 90,
                            spread = (270..360).random(),
                            speed = 0F,
                            maxSpeed = 40F,
                            damping = 0.9F,
                            colors = listOf(AppColors.confetti1, AppColors.confetti2, AppColors.confetti3, AppColors.confetti4),
                            fadeOutEnabled = true,
                            position = Position.Relative(0.5, 1.0),
                            emitter = Emitter(duration = (550..750).random().toLong(), TimeUnit.MILLISECONDS).max(500)
                        ),
                        Party(
                            angle = 90,
                            spread = (270..360).random(),
                            speed = 0F,
                            maxSpeed = 40F,
                            damping = 0.9F,
                            colors = listOf(AppColors.confetti1, AppColors.confetti2, AppColors.confetti3, AppColors.confetti4),
                            fadeOutEnabled = true,
                            position = Position.Relative(0.5, 0.0),
                            emitter = Emitter(duration = (550..750).random().toLong(), TimeUnit.MILLISECONDS).max(500)
                        ),
                        Party(
                            angle = 360,
                            spread = 360,
                            speed = 0F,
                            maxSpeed = 40F,
                            damping = 0.9F,
                            colors = listOf(AppColors.confetti1, AppColors.confetti2, AppColors.confetti3, AppColors.confetti4),
                            fadeOutEnabled = true,
                            position = Position.Relative(0.5, 0.5),
                            emitter = Emitter(duration = (550..750).random().toLong(), TimeUnit.MILLISECONDS).max(500)
                        )
                    )
                }
            )
        }
    }
}