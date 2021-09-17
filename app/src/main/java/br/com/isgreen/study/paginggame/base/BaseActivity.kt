package br.com.isgreen.study.paginggame.base

import android.os.Build
import android.view.View
import android.view.WindowInsetsController
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.BuildCompat.isAtLeastR

/**
 * Created by Éverdes Soares on 12/11/2020.
 */

abstract class BaseActivity : AppCompatActivity() {

    protected fun changeStatusBarIconsColor(isIconsWhite: Boolean) {
        if (isAtLeastR()) {
            if (isIconsWhite) {
                window?.insetsController?.setSystemBarsAppearance(
                    0,
                    WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
                )
            } else {
                window?.insetsController?.setSystemBarsAppearance(
                    WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
                    WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
                )
            }
        } else {
            window.decorView.systemUiVisibility = if (
                isIconsWhite || Build.VERSION.SDK_INT < Build.VERSION_CODES.M
            ) {
                0
            } else {
                View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            }
        }
    }

}