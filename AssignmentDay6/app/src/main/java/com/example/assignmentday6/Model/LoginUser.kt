package com.example.assignmentday6.Model

import android.text.TextUtils
import android.util.Patterns

class LoginUser(val email: String, val password: String) {
    val isDataValid: Boolean
        get() = (!TextUtils.isEmpty(email))
                && Patterns.EMAIL_ADDRESS.matcher(email).matches()
                && password.length > 6
}