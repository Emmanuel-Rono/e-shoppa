package com.emmanuel_rono.e_shoppa.presentation.Activities

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import com.emmanuel_rono.e_shoppa.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Hide the status bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            // Hide the status bar
            window.decorView.systemUiVisibility = hideStatusBarFlags()
            setContentView(R.layout.activity_main)
        }
    }
    private fun hideStatusBarFlags(): Int {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        } else {
            @Suppress("DEPRECATION")
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        }
    }
}