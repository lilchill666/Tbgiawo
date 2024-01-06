package com.lilchill.tbgiawo.view.layouts

import android.content.Context
import android.graphics.Color
import android.graphics.Rect
import android.util.Log
import android.view.Gravity
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.FrameLayout
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.get
import com.google.android.material.card.MaterialCardView
import com.lilchill.tbgiawo.R
import com.lilchill.tbgiawo.presenter.GamePresenter
import com.lilchill.tbgiawo.view.layouts.views.GameField
import com.lilchill.tbgiawo.view.layouts.views.GamePointer
import nl.dionsegijn.konfetti.xml.KonfettiView

class GameLayout(context: Context) : FrameLayout(context) {
    private val fieldSize = resources.displayMetrics.heightPixels * 73 / 100
    val gameField = GameField(context, fieldSize)
    val pointer = GamePointer(context, gameField.distance)
    val confetti = KonfettiView(context)
    private val helpFieldRect = Rect()
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
                strokeColor = Color.parseColor("#0F0A58")
                strokeWidth = 7
                setCardBackgroundColor(Color.TRANSPARENT)
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
    }
}