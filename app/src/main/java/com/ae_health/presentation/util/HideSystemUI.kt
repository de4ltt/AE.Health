package com.ae_health.presentation.util

import android.app.Activity
import android.os.Build
import android.view.Window
import android.view.WindowManager
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.ae_health.presentation.MainActivity

fun MainActivity.hideSystemUI() = with(this) {

    fun Window.hideSystemUi(extraAction: (WindowInsetsControllerCompat.() -> Unit)? = null) {
        WindowInsetsControllerCompat(this, this.decorView).let { controller ->
            controller.hide(WindowInsetsCompat.Type.systemBars())
            extraAction?.invoke(controller)
        }
    }

    fun Activity.setDisplayCutoutMode() {
        when {
            Build.VERSION.SDK_INT < Build.VERSION_CODES.R -> {
                window.attributes.layoutInDisplayCutoutMode =
                    WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
            }
        }
    }

    WindowCompat.setDecorFitsSystemWindows(window, false)
    window.hideSystemUi(extraAction = {
        systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
    })
    setDisplayCutoutMode()
}