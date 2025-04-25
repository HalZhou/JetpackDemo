package com.example.jetpack.common.utils

import android.os.Build
import androidx.annotation.ChecksSdkIntAtLeast

object OsVersionUtils {

    // 6.0 23 M

    // 7.0 24 N

    // 7.1 25 N_MR1
    @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.N_MR1)
    @JvmStatic
    fun checkSdkIntAtLeast25() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1

    // 8.0 26 O
    @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.O)
    @JvmStatic
    fun checkSdkIntAtLeast26() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O
    // 8.1 27 O_MR1
    @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.O_MR1)
    @JvmStatic
    fun checkSdkIntAtLeast27() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1

    // 9.0 28 P
    @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.P)
    @JvmStatic
    fun checkSdkIntAtLeast28() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.P

    // 10.0 Q 29
    @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.Q)
    @JvmStatic
    fun checkSdkIntAtLeast29() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q

    // 11.0 R 30
    @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.R)
    @JvmStatic
    fun checkSdkIntAtLeast30() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.R

    // 12.0 S 31
    @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.S)
    @JvmStatic
    fun checkSdkIntAtLeast31() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
    // 12.1 S_V2 32
    @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.S_V2)
    @JvmStatic
    fun checkSdkIntAtLeast32() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S_V2

    // 13.0 TIRAMISU 33
    @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.TIRAMISU)
    @JvmStatic
    fun checkSdkIntAtLeast33() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU

    // 14.0 UPSIDE_DOWN_CAKE  34
    @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    @JvmStatic
    fun checkSdkIntAtLeast34() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE
}