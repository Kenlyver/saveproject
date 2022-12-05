package com.example.assigmentday4

import android.content.Context
import android.content.SharedPreferences


object AppPreferences {
    private const val NAME = "AssigmentDay4"
    private const val MODE = Context.MODE_PRIVATE
    private lateinit var preferences: SharedPreferences

    private val BRIGHTNESS = Pair("brightness", "")
    private val SCREENMODE = Pair("screenmode", "Day mode")
    private val AUTOBRIGHTNESS = Pair("autobrightness", false)
    private val LIGHTBLUE = Pair("lightblue", false)


    fun init(context: Context) {
        preferences = context.getSharedPreferences(NAME, MODE)
    }

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }


    var brightness: String?
        get() = preferences.getString(BRIGHTNESS.first, BRIGHTNESS.second)
        set(value) = preferences.edit {
            it.putString(BRIGHTNESS.first, value)
        }

    var screenmode: String?
        get() = preferences.getString(SCREENMODE.first, SCREENMODE.second)
        set(value) = preferences.edit {
            it.putString(SCREENMODE.first, value)
        }


    var autobrightness: Boolean
        get() = preferences.getBoolean(AUTOBRIGHTNESS.first, AUTOBRIGHTNESS.second)
        set(value) = preferences.edit {
            it.putBoolean(AUTOBRIGHTNESS.first, value)
        }
    var lightblue: Boolean
        get() = preferences.getBoolean(LIGHTBLUE.first, LIGHTBLUE.second)
        set(value) = preferences.edit {
            it.putBoolean(LIGHTBLUE.first, value)
        }
}