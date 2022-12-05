package com.example.animationzingcover

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.core.content.ContextCompat
import com.example.animationzingcover.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var pause = true
    lateinit var binding: ActivityMainBinding
    private var count = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        changeIcon()
        setContentView(binding.root)
        val animation = AnimationUtils.loadAnimation(this, R.anim.example)
        binding.imgPlay.setOnClickListener {
            if (count % 2 == 0) {
                animation.cancel()
            } else {
                binding.imgCircle.startAnimation(animation)
            }
            count++
            pause = !pause
            changeIcon()
        }
    }

    fun changeIcon() {
        if (!pause) {
            binding.imgPlay.setImageDrawable(
                ContextCompat.getDrawable(application, R.drawable.playing)
            )
        } else {
            binding.imgPlay.setImageDrawable(
                ContextCompat.getDrawable(application, R.drawable.play)

            )
        }
    }
}


