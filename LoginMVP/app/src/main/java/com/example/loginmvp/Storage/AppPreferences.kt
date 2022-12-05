package com.example.loginmvp.Storage

import android.content.Context
import android.content.SharedPreferences


object AppPreferences {
    private const val NAME = "LoginMVP"
    private const val MODE = Context.MODE_PRIVATE
    private lateinit var preferences: SharedPreferences

    private val IS_LOGIN = Pair("is_login", false)
    private val USERNAME = Pair("username", "")
    private val PASSWORD = Pair("password", "")


    fun init(context: Context) {
        preferences = context.getSharedPreferences(NAME, MODE)
    }

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    var isLogin: Boolean
        get() = preferences.getBoolean(IS_LOGIN.first, IS_LOGIN.second)
        set(value) = preferences.edit {
            it.putBoolean(IS_LOGIN.first, value)
        }
    var username: String
        get() = preferences.getString(USERNAME.first, USERNAME.second) ?: ""
        set(value) = preferences.edit {
            it.putString(USERNAME.first, value)
        }

    var password: String
        get() = preferences.getString(PASSWORD.first, PASSWORD.second) ?: ""
        set(value) = preferences.edit {
            it.putString(PASSWORD.first, value)
        }
}