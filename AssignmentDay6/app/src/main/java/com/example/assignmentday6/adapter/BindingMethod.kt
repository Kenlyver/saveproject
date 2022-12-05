package com.example.assignmentday6.adapter

import android.widget.Button
import androidx.databinding.BindingMethod
import androidx.databinding.BindingMethods

@BindingMethods(
    BindingMethod(
        type = Button::class,
        attribute = "app:name",
        method = "setText"
    )
)
class BindingMethod