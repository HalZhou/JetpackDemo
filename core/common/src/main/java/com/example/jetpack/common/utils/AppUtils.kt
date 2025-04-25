package com.example.jetpack.common.utils

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build

@SuppressLint("StaticFieldLeak")
object AppUtils {

    @JvmStatic
    private var mContext: Context? = null

    @JvmStatic
    fun inject(context: Context) {
        this.mContext = context
    }

    @JvmStatic
    fun getContext() : Context {
        if (mContext == null) throw IllegalStateException("Application must be inject context into AppUtils")
        return mContext!!
    }

    @JvmStatic
    fun getAppVersionName(): String {
        val c = mContext ?: return ""

        return try {
            val pm = c.packageManager
            val pi = pm.getPackageInfo(c.packageName, 0)
            pi.versionName
        } catch (e: Exception) {
            ""
        }
    }

    @JvmStatic
    fun getAppVersionCode(): Int {
        val c = mContext ?: return 0
        return try {
            val pm = c.packageManager
            val pi = pm.getPackageInfo(c.packageName, 0)
            if (OsVersionUtils.checkSdkIntAtLeast28()) {
                pi.longVersionCode.toInt()
            } else {
                pi.versionCode
            }
        } catch (e: Exception) {
            0
        }
    }

    @JvmStatic
    fun getSystemVersion() = Build.VERSION.RELEASE

    @JvmStatic
    fun getSystemModel() = Build.MODEL

    @JvmStatic
    fun getMANUFACTURER() = Build.MANUFACTURER

}