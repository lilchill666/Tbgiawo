package com.lilchill.tbgiawo.view.layouts

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.drawable.GradientDrawable
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.forEach
import androidx.core.view.forEachIndexed
import androidx.core.view.get
import androidx.core.view.setPadding
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView.Orientation
import com.google.android.material.button.MaterialButton
import com.lilchill.tbgiawo.R
import com.lilchill.tbgiawo.view.AppActivity
import com.lilchill.tbgiawo.view.layouts.views.GamePointer
import org.w3c.dom.Text

class GameStartDialogLayout(context: Context) : ConstraintLayout(context) {
    var chosenFirstPlayer = MutableLiveData(2)
    var playerOneColor = MutableLiveData(Color.RED)
    var playerTwoColor = MutableLiveData(Color.BLACK)
    val proceedButton = MaterialButton(context)
    private val firstPlayerText = TextView(context)
    private val firstPlayerPickerView = FrameLayout(context)
    private val firstPlayerColorText = TextView(context)
    private val firstPlayerColorPickerView = FrameLayout(context)
    private val secondPlayerColorText = TextView(context)
    private val secondPlayerColorPickerView = FrameLayout(context)
    init {
        background = GradientDrawable().apply {
            cornerRadius = 52F
            color = ColorStateList.valueOf(Color.parseColor("#2D5F6B"))
        }
        setupTexts()
        setupButton()
        setupFirstPlayerPicker()
        setupColorPickers()
        addView(
            firstPlayerText,
            LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT
            ).apply {
                this.endToEnd = LayoutParams.PARENT_ID
                this.startToStart = LayoutParams.PARENT_ID
                this.topToTop = LayoutParams.PARENT_ID
                this.setMargins((resources.displayMetrics.density * 16).toInt(), (resources.displayMetrics.density * 10).toInt(), (resources.displayMetrics.density * 16).toInt(), 0)
            }
        )
        addView(
            firstPlayerPickerView,
            LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT
            ).apply {
                this.endToEnd = LayoutParams.PARENT_ID
                this.startToStart = LayoutParams.PARENT_ID
                this.topToBottom = firstPlayerText.id
                this.setMargins((resources.displayMetrics.density * 16).toInt(), (resources.displayMetrics.density * 2).toInt(), (resources.displayMetrics.density * 16).toInt(), 0)
            }
        )
        addView(
            firstPlayerColorText,
            LayoutParams(
                (resources.displayMetrics.density * 160).toInt(),
                LayoutParams.WRAP_CONTENT
            ).apply {
                this.startToStart = LayoutParams.PARENT_ID
                this.topToBottom = firstPlayerPickerView.id
                this.setMargins((resources.displayMetrics.density * 16).toInt(), (resources.displayMetrics.density * 5).toInt(), 0, 0)
            }
        )
        addView(
            secondPlayerColorText,
            LayoutParams(
                (resources.displayMetrics.density * 160).toInt(),
                LayoutParams.WRAP_CONTENT
            ).apply {
                this.endToEnd = LayoutParams.PARENT_ID
                this.topToBottom = firstPlayerPickerView.id
                this.setMargins(0, (resources.displayMetrics.density * 5).toInt(), (resources.displayMetrics.density * 16).toInt(), 0)
            }
        )
        addView(
            firstPlayerColorPickerView,
            LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT
            ).apply {
                this.startToStart = LayoutParams.PARENT_ID
                this.topToBottom = firstPlayerColorText.id
                this.setMargins((resources.displayMetrics.density * 16).toInt(), (resources.displayMetrics.density * 7).toInt(), 0, 0)
            }
        )
        addView(
            secondPlayerColorPickerView,
            LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT
            ).apply {
                this.endToEnd = LayoutParams.PARENT_ID
                this.topToBottom = secondPlayerColorText.id
                this.setMargins(0, (resources.displayMetrics.density * 7).toInt(), (resources.displayMetrics.density * 16).toInt(), 0)
            }
        )
        addView(
            proceedButton,
            LayoutParams(
                (resources.displayMetrics.density * 100).toInt(),
                (resources.displayMetrics.density * 50).toInt()
            ).apply {
                this.startToStart = LayoutParams.PARENT_ID
                this.endToEnd = LayoutParams.PARENT_ID
                this.topToBottom = secondPlayerColorPickerView.id
                this.bottomToBottom = LayoutParams.PARENT_ID
                this.setMargins(0, (resources.displayMetrics.density * 2).toInt(), 0, (resources.displayMetrics.density * 6).toInt())
            }
        )
    }
    private fun setupTexts(){
        firstPlayerText.text = resources.getString(R.string.firstPlayerText)
        firstPlayerText.typeface = resources.getFont(R.font.luckiest_guy)
        firstPlayerText.textSize = 26F
        firstPlayerText.setTextColor(Color.WHITE)
        firstPlayerText.gravity = Gravity.CENTER
        firstPlayerText.textAlignment = TEXT_ALIGNMENT_CENTER
        firstPlayerText.id = View.generateViewId()
        firstPlayerColorText.text = resources.getString(R.string.firstPlayerColorText)
        firstPlayerColorText.typeface = resources.getFont(R.font.luckiest_guy)
        firstPlayerColorText.textSize = 22F
        firstPlayerColorText.setTextColor(Color.WHITE)
        firstPlayerColorText.gravity = Gravity.CENTER
        firstPlayerColorText.textAlignment = TEXT_ALIGNMENT_CENTER
        firstPlayerColorText.id = View.generateViewId()
        secondPlayerColorText.text = resources.getString(R.string.secondPlayerColorText)
        secondPlayerColorText.typeface = resources.getFont(R.font.luckiest_guy)
        secondPlayerColorText.textSize = 22F
        secondPlayerColorText.setTextColor(Color.WHITE)
        secondPlayerColorText.gravity = Gravity.CENTER
        secondPlayerColorText.textAlignment = TEXT_ALIGNMENT_CENTER
        secondPlayerColorText.id = View.generateViewId()
    }
    private fun setupButton(){
        proceedButton.text = resources.getString(R.string.buttonText)
        proceedButton.textSize = 18F
        proceedButton.typeface = resources.getFont(R.font.luckiest_guy)
        proceedButton.rippleColor = null
        proceedButton.setTextColor(Color.WHITE)
        proceedButton.cornerRadius = 22
        proceedButton.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#191749"))
    }

    private fun setupFirstPlayerPicker(){
        firstPlayerPickerView.id = View.generateViewId()
        repeat(3){
            firstPlayerPickerView.addView(
                MaterialButton(context).apply {
                    text = resources.getString(
                        when (it){
                            0 -> R.string.firstPlayerOption
                            1 -> R.string.secondPlayerOption
                            2 -> R.string.randomPlayerOption
                            else -> 0
                        }
                    )
                    typeface = resources.getFont(R.font.luckiest_guy)
                    rippleColor = null
                    setTextColor(Color.WHITE)
                    cornerRadius = 58
                    textSize = 20F
                    setPadding((resources.displayMetrics.density * 3).toInt())
                    backgroundTintList = ColorStateList.valueOf(Color.parseColor("#191749"))
                    setOnClickListener {
                        chosenFirstPlayer.value = firstPlayerPickerView.indexOfChild(it)
                    }
                },
                FrameLayout.LayoutParams(
                    (resources.displayMetrics.density * 110).toInt(),
                    (resources.displayMetrics.density * 55).toInt()
                ).apply {
                    this.gravity = Gravity.CENTER_VERTICAL or Gravity.START
                    this.setMargins((resources.displayMetrics.density * 3).toInt() + (resources.displayMetrics.density * 116).toInt() * it, 0, 0, 0)
                }
            )
        }
        chosenFirstPlayer.observe((context as AppActivity).supportFragmentManager.fragments.last()){
            firstPlayerPickerView.forEachIndexed { i, v ->
                v as MaterialButton
                if (i == it){
                    v.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#0C0C19"))
                } else {
                    v.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#191749"))
                }
            }
        }
    }
    private fun setupColorPickers(){
        val m = (resources.displayMetrics.density * 5).toInt()
        val list = listOf(
            listOf(Color.RED, Color.GREEN, Color.BLACK),
            listOf(Color.YELLOW, Color.GRAY, Color.DKGRAY),
            listOf(Color.BLUE, Color.CYAN, Color.MAGENTA)
        )
        secondPlayerColorPickerView.id = View.generateViewId()
        repeat(3){row ->
            firstPlayerColorPickerView.addView(
                LinearLayout(context).apply {
                    this.orientation = LinearLayout.HORIZONTAL
                    repeat(3){column ->
                        addView(
                            GamePointer(context).apply {
                                this.updateDrawable(list[row][column])
                                setOnClickListener {
                                    if (list[row][column] != playerTwoColor.value){
                                        playerOneColor.value = list[row][column]
                                    }
                                }
                            },
                            FrameLayout.LayoutParams(
                                (resources.displayMetrics.density * 38).toInt(),
                                (resources.displayMetrics.density * 38).toInt()
                            ).apply {
                                this.setMargins(m, m, m, m)
                            }
                        )
                    }
                },
                FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.WRAP_CONTENT,
                    FrameLayout.LayoutParams.WRAP_CONTENT
                ).apply {
                    this.gravity = Gravity.TOP or Gravity.CENTER_HORIZONTAL
                    this.setMargins(0, (resources.displayMetrics.density * 43).toInt() * row + 1, 0, 0)
                }
            )
        }
        repeat(3){row ->
            secondPlayerColorPickerView.addView(
                LinearLayout(context).apply {
                    this.orientation = LinearLayout.HORIZONTAL
                    repeat(3){column ->
                        addView(
                            GamePointer(context).apply {
                                this.updateDrawable(list[row][column])
                                setOnClickListener {
                                    if (list[row][column] != playerOneColor.value) {
                                        playerTwoColor.value = list[row][column]
                                    }
                                }
                            },
                            FrameLayout.LayoutParams(
                                (resources.displayMetrics.density * 38).toInt(),
                                (resources.displayMetrics.density * 38).toInt()
                            ).apply {
                                this.setMargins(m, m, m, m)
                            }
                        )
                    }
                },
                FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.WRAP_CONTENT,
                    FrameLayout.LayoutParams.WRAP_CONTENT
                ).apply {
                    this.gravity = Gravity.TOP or Gravity.CENTER_HORIZONTAL
                    this.setMargins(0, (resources.displayMetrics.density * 43).toInt() * row + 1, 0, 0)
                }
            )
        }
        playerOneColor.observe((context as AppActivity).supportFragmentManager.fragments.last()){value ->
            firstPlayerColorPickerView.forEachIndexed { index, view ->
                view as LinearLayout
                view.forEachIndexed {index1, view1 ->
                    if (list[index][index1] == value){
                        view1.scaleX = 1.2F
                        view1.scaleY = 1.2F
                    } else {
                        view1.scaleX = 0.9F
                        view1.scaleY = 0.9F
                    }
                }
            }
        }
        playerTwoColor.observe((context as AppActivity).supportFragmentManager.fragments.last()){value ->
            secondPlayerColorPickerView.forEachIndexed { index, view ->
                view as LinearLayout
                view.forEachIndexed {index1, view1 ->
                    if (list[index][index1] == value){
                        view1.scaleX = 1.2F
                        view1.scaleY = 1.2F
                    } else {
                        view1.scaleX = 0.9F
                        view1.scaleY = 0.9F
                    }
                }
            }
        }
    }
}