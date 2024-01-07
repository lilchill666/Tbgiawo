package com.lilchill.tbgiawo.view.layouts.views

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.GradientDrawable
import android.view.Gravity
import androidx.appcompat.widget.AppCompatTextView
import com.lilchill.tbgiawo.R
import com.lilchill.tbgiawo.constants.AppColors

class GameToast(context: Context) : AppCompatTextView(context) {
    init {
        background = GradientDrawable().apply {
            val padding = (resources.displayMetrics.density * 5F).toInt()
            this.color = ColorStateList.valueOf(AppColors.gameToastBackgroundColor)
            this.setStroke(5, ColorStateList.valueOf(AppColors.gameToastStrokeColor))
            this.setPadding(padding, padding, padding, padding)
            this.cornerRadius = 42F
        }
        setTextColor(ColorStateList.valueOf(AppColors.gameToastTextColor))
        typeface = resources.getFont(R.font.luckiest_guy)
        textSize = 38F
        this.gravity = Gravity.CENTER
        this.textAlignment = TEXT_ALIGNMENT_CENTER
    }
}