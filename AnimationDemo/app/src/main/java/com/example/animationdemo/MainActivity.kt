package com.example.animationdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import com.example.animationdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val animation = AnimationUtils.loadAnimation(this,R.anim.example)
        binding.imgView.setOnClickListener {
            it.startAnimation(animation)
        }
        binding.btnRotate.setOnClickListener {
            val animation = AnimationUtils.loadAnimation(this,R.anim.rotate)
            binding.imgView.startAnimation(animation)
        }
        binding.btnDown.setOnClickListener {
            val animation = AnimationUtils.loadAnimation(this,R.anim.slide_down)
            binding.imgView.startAnimation(animation)
        }
        binding.btnUp.setOnClickListener {
            val animation = AnimationUtils.loadAnimation(this,R.anim.slide_up)
            binding.imgView.startAnimation(animation)
        }
        binding.btnZoomIn.setOnClickListener {
            val animation = AnimationUtils.loadAnimation(this,R.anim.zoom_in)
            binding.imgView.startAnimation(animation)
        }
        binding.btnZoomOut.setOnClickListener {
            val animation = AnimationUtils.loadAnimation(this,R.anim.zoom_out)
            binding.imgView.startAnimation(animation)
        }
        setContentView(binding.root)
    }
}