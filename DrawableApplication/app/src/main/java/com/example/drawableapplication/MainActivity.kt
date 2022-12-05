package com.example.drawableapplication

import android.graphics.BitmapFactory
import android.graphics.drawable.AnimationDrawable
import android.graphics.drawable.TransitionDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.drawableapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val bitmapDrawable = BitmapFactory.decodeResource(
            resources,
            R.drawable.facebook
        )
        binding.img1.setImageBitmap(bitmapDrawable)
        binding.btnExample.setOnClickListener {
            val animation = binding.img3.background as AnimationDrawable
            animation.start()
        }
        binding.btnShape2.setOnClickListener {
            val animation = binding.img3.background as AnimationDrawable
            animation.stop()
        }
        binding.img4.setImageLevel(3)
        val lightDrawable = binding.img6.drawable as TransitionDrawable?
        binding.switch1.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                lightDrawable?.startTransition(4000)
            } else {
                lightDrawable?.reverseTransition(4000)
            }
        }
    }
}