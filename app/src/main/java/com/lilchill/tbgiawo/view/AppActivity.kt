package com.lilchill.tbgiawo.view

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.InputQueue
import android.view.WindowManager
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.lilchill.tbgiawo.R
import com.lilchill.tbgiawo.constants.AppKeys
import com.lilchill.tbgiawo.view.fragments.MenuFragment
import com.tencent.mmkv.MMKV

//Commit
//Added splash screen
//Added animation for dialog appearing in local game
//Added color picking animation in local game dialog
//Added match checker in local game
//Added game field reset animation
//Added game end toast that indicates win or tie
//Added win animation(will be only visible for winner in online mode)
//Moved all app colors to GameColors object

//GameToast text in presenter since it will be different for online and local mode
//settings and settings animation
//correct drawables with flare and stroke
//nice colors for dialog and game elements colors
class AppActivity : AppCompatActivity() {
    val storage: MMKV by lazy { MMKV.defaultMMKV(MMKV.SINGLE_PROCESS_MODE, AppKeys.MMKV_ENCRYPT_KEY) }
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        val windowInsetsController = WindowCompat.getInsetsController(window, window.decorView)
        windowInsetsController.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        windowInsetsController.hide(WindowInsetsCompat.Type.systemBars())
        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.navigationBarColor = 0
        window.statusBarColor = 0
        super.onCreate(savedInstanceState)
        setContentView(R.layout.app_activity)
        supportFragmentManager.beginTransaction().add(R.id.root, MenuFragment()).commit()
    }

}