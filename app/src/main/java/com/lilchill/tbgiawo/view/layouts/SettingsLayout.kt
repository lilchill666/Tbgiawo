package com.lilchill.tbgiawo.view.layouts

import android.content.Context
import android.widget.FrameLayout
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import com.lilchill.tbgiawo.R
import com.lilchill.tbgiawo.view.interfaces.SettingsViewInterface

class SettingsLayout(context: Context) : FrameLayout(context) {
    init {
        background = AppCompatResources.getDrawable(context, R.drawable.background)
    }
}