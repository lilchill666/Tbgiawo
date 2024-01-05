package com.lilchill.tbgiawo

import android.app.Application
import com.tencent.mmkv.MMKV

class TbgiawoApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        MMKV.initialize(this)
    }
}