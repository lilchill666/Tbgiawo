package com.lilchill.tbgiawo.view

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.lilchill.tbgiawo.R
import com.lilchill.tbgiawo.constants.AppKeys
import com.lilchill.tbgiawo.view.fragments.MenuFragment
import com.tencent.mmkv.MMKV

//splash
//column pointer
//falling animation (translationY end point should be calculated in GameField class by finding first untaken + margin)
//match checker
//arrows (game style(ask Lera))
class AppActivity : AppCompatActivity() {
    val storage: MMKV by lazy { MMKV.defaultMMKV(MMKV.SINGLE_PROCESS_MODE, AppKeys.MMKV_ENCRYPT_KEY) }
    override fun onCreate(savedInstanceState: Bundle?) {
        val windowInsetsController = WindowCompat.getInsetsController(window, window.decorView)
        windowInsetsController.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        windowInsetsController.hide(WindowInsetsCompat.Type.systemBars())
        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.navigationBarColor = 0
        window.statusBarColor = 0
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.app_activity)
        supportFragmentManager.beginTransaction().add(R.id.root, MenuFragment()).commit()
    }

}