package com.example.jectpackdemo

import android.app.Application
import com.example.jetpack.common.utils.AppUtils
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class JetpackApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        AppUtils.inject(this)
    }
}