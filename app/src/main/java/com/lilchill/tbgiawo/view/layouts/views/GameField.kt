package com.lilchill.tbgiawo.view.layouts.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.SweepGradient
import android.graphics.drawable.GradientDrawable
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import com.lilchill.tbgiawo.constants.AppColors
import com.lilchill.tbgiawo.model.data.GameCellState
import com.lilchill.tbgiawo.model.data.entities.GameCell
import kotlin.random.Random


class GameField(context: Context) : FrameLayout(context) {
    val cells = List(7){ mutableListOf<GameCell>() }
    var distance = 0F
    constructor(context: Context, size: Int) : this(context){
        val diameter = size / 6
        val margin = (context.resources.displayMetrics.density * 8F).toInt()
        val padding = (margin * 1.5F).toInt()
        distance = (size / 6 + margin).toFloat()
        repeat(7){ column ->
            repeat(6){ row ->
                addView(
                    ImageView(context).also {
                        cells[column].add(GameCell(it, GameCellState.Empty))
                    },
                    LayoutParams(diameter, diameter).apply {
                        gravity = Gravity.TOP or Gravity.START
                        setMargins(diameter * column + margin * column + padding, diameter * row + margin * row + padding, 0, 0)
                    }
                )
            }
        }
        addView(
            View(context).apply {
                background = object : GradientDrawable() {
                    private val colors = intArrayOf(
                        AppColors.gameFieldRingGradientColorOne,
                        AppColors.gameFieldRingGradientColorTwo,
                        AppColors.gameFieldRingGradientColorOne
                    )
                    private val ringThickness = context.resources.displayMetrics.density * 3.2F
                    private val positions get() = floatArrayOf(0f, Random.nextDouble(0.5, 0.8).toFloat(), 1f)
                    private val ringPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
                        style = Paint.Style.STROKE
                        strokeWidth = ringThickness
                    }
                    private val radius = diameter / 2f
                    private val backgroundPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
                        color = AppColors.gameFieldBackground
                    }
                    private val clearPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
                        xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
                    }
                    override fun draw(canvas: Canvas) {
                        val bounds = bounds
                        canvas.saveLayer(bounds.left.toFloat(), bounds.top.toFloat(), bounds.right.toFloat(), bounds.bottom.toFloat(), null)
                        canvas.drawRect(bounds, backgroundPaint)
                        repeat(7){ column ->
                            repeat(6){ row ->
                                val cx = diameter * column + margin * column + diameter / 2f + padding
                                val cy = diameter * row + margin * row + diameter / 2f + padding
                                ringPaint.shader = SweepGradient(cx, cy, colors, positions)
                                canvas.save()
                                canvas.rotate(Random.nextFloat() * 360, cx, cy)
                                canvas.drawCircle(cx, cy, radius - ringThickness / 2, ringPaint)
                                canvas.restore()
                                canvas.drawCircle(cx, cy, radius - ringThickness, clearPaint)
                            }
                        }
                        canvas.restore()
                    }
                }
            },
            LayoutParams(
                diameter * 7 + margin * 7 + (padding * 1.5F).toInt(),
                diameter * 6 + margin * 6 + (padding * 1.5F).toInt()
            )
        )
    }
}


