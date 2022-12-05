package com.example.sentlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.sentlist.databinding.ActivityActivityBinding

class ActivityA : AppCompatActivity() {
    lateinit var binding: ActivityActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityActivityBinding.inflate(layoutInflater)
        val list = intent.getSerializableExtra("sentList")
        Log.d("show", list.toString())
        val listTemp = removeChar(list.toString(), '[', ']')
        binding.txtList.text = listTemp.toString()
        setContentView(binding.root)
    }

    fun removeChar(s: String?, c: Char, cc: Char): String? {
        if (s == null || s.isEmpty()) {
            return s
        }
        return if (s.startsWith(c) && s.endsWith(cc)) {
            val temp = s.drop(1)
            temp.dropLast(1)
        } else s
    }

}