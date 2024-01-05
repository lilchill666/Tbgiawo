package com.lilchill.tbgiawo.view.layouts

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.Gravity
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import com.lilchill.tbgiawo.R

class MenuLayout(context: Context) : FrameLayout(context) {
    private val margin = (20F * resources.displayMetrics.density).toInt()
    val startLocallyButton = FrameLayout(context)
    val startOnlineButton = FrameLayout(context)
    val finishButton = FrameLayout(context)
    val wins = TextView(context)
    val settings = ImageView(context)
    init {
        background = AppCompatResources.getDrawable(context, R.drawable.background)
        setupButtons()
        setupWins()
        addView(
            startLocallyButton,
            LayoutParams(
                (520F * resources.displayMetrics.density).toInt(),
                resources.displayMetrics.heightPixels / 3
            ).apply {
                this.gravity = Gravity.TOP or Gravity.CENTER_HORIZONTAL
                this.setMargins(0, margin, 0, 0)
            }
        )
        addView(
            startOnlineButton,
            LayoutParams(
                (520F * resources.displayMetrics.density).toInt(),
                resources.displayMetrics.heightPixels / 3
            ).apply {
                this.gravity = Gravity.TOP or Gravity.CENTER_HORIZONTAL
                this.setMargins(0, resources.displayMetrics.heightPixels / 3, 0, 0)
            }
        )
        addView(
            finishButton,
            LayoutParams(
                (520F * resources.displayMetrics.density).toInt(),
                resources.displayMetrics.heightPixels / 3
            ).apply {
                this.gravity = Gravity.TOP or Gravity.CENTER_HORIZONTAL
                this.setMargins(0, (resources.displayMetrics.heightPixels * 0.5F + margin * 2.3).toInt(), 0, 0)
            }
        )
        addView(
            wins,
            LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT
            ).apply {
                gravity = Gravity.TOP or Gravity.END
                setPadding(0, (12F * resources.displayMetrics.density).toInt(), (30F * resources.displayMetrics.density).toInt(), 0)
            }
        )
        addView(
            settings.apply{
                this.setImageDrawable(AppCompatResources.getDrawable(context, R.drawable.settings))
            },
            LayoutParams(
                (85F * resources.displayMetrics.density).toInt(),
                (85F * resources.displayMetrics.density).toInt()
            ).apply {
                this.gravity = Gravity.TOP or Gravity.START
                this.setMargins((30F * resources.displayMetrics.density).toInt(), (12F * resources.displayMetrics.density).toInt(), 0, 0)
            }
        )
    }
    private fun setupButtons(){
        startLocallyButton.apply {
            this.addView(
                ImageView(context).apply {
                    this.setImageDrawable(AppCompatResources.getDrawable(context, R.drawable.button))
                },
                LayoutParams(
                    LayoutParams.MATCH_PARENT,
                    LayoutParams.MATCH_PARENT
                )
            )
            this.addView(
                TextView(context).apply {
                    text = resources.getString(R.string.localMenuButton)
                    typeface = resources.getFont(R.font.luckiest_guy)
                    textSize = 40F
                    gravity = Gravity.CENTER
                    textAlignment = TEXT_ALIGNMENT_CENTER
                    setTextColor(Color.WHITE)
                },
                LayoutParams(
                    LayoutParams.MATCH_PARENT,
                    LayoutParams.MATCH_PARENT
                )
            )
        }
        startOnlineButton.apply {
            this.addView(
                ImageView(context).apply {
                    this.setImageDrawable(AppCompatResources.getDrawable(context, R.drawable.button))
                },
                LayoutParams(
                    LayoutParams.MATCH_PARENT,
                    LayoutParams.MATCH_PARENT
                )
            )
            this.addView(
                TextView(context).apply {
                    text = resources.getString(R.string.onlineMenuButton)
                    typeface = resources.getFont(R.font.luckiest_guy)
                    textSize = 40F
                    gravity = Gravity.CENTER
                    textAlignment = TEXT_ALIGNMENT_CENTER
                    setTextColor(Color.WHITE)
                },
                LayoutParams(
                    LayoutParams.MATCH_PARENT,
                    LayoutParams.MATCH_PARENT
                )
            )
        }
        finishButton.apply {
            this.addView(
                ImageView(context).apply {
                    this.setImageDrawable(AppCompatResources.getDrawable(context, R.drawable.button))
                },
                LayoutParams(
                    LayoutParams.MATCH_PARENT,
                    LayoutParams.MATCH_PARENT
                )
            )
            this.addView(
                TextView(context).apply {
                    text = resources.getString(R.string.exitMenuButton)
                    typeface = resources.getFont(R.font.luckiest_guy)
                    textSize = 40F
                    gravity = Gravity.CENTER
                    textAlignment = TEXT_ALIGNMENT_CENTER
                    setTextColor(Color.WHITE)
                },
                LayoutParams(
                    LayoutParams.MATCH_PARENT,
                    LayoutParams.MATCH_PARENT
                )
            )
        }
    }
    private fun setupWins() {
        wins.gravity = Gravity.CENTER
        wins.textAlignment = TEXT_ALIGNMENT_CENTER
        wins.textSize = 50F
        wins.setTextColor(Color.WHITE)
        wins.typeface = resources.getFont(R.font.luckiest_guy)
        wins.setShadowLayer(15F, 5F, 5F, Color.BLACK)
    }
}