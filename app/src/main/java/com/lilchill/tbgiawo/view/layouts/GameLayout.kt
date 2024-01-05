package com.lilchill.tbgiawo.view.layouts

import android.content.Context
import android.graphics.Color
import android.view.Gravity
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.content.res.AppCompatResources
import com.google.android.material.card.MaterialCardView
import com.lilchill.tbgiawo.R
import com.lilchill.tbgiawo.view.layouts.views.GameField

class GameLayout(context: Context) : FrameLayout(context) {
    private val fieldSize = resources.displayMetrics.heightPixels * 78 / 100
    val gameField = GameField(context, fieldSize)
    init {
        background = AppCompatResources.getDrawable(context, R.drawable.background)
        addView(
            MaterialCardView(context).apply {
                addView(
                    gameField,
                    LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
                )
                radius = context.resources.displayMetrics.density * 22F
                strokeColor = Color.parseColor("#0F0A58")
                strokeWidth = 7
                setCardBackgroundColor(Color.TRANSPARENT)
            },
            LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT).apply {
                this.gravity = Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL
                this.setMargins(0, 0, 0, (resources.displayMetrics.density * 20F).toInt())
            }
        )
    }
}