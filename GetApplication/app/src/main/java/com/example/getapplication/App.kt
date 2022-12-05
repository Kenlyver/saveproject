package com.example.getapplication

import android.graphics.drawable.Drawable

data class App(
    val appName: String,
    val icon: Drawable,
    val packages: String,
    val installTime: String
)
