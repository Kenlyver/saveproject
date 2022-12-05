package com.example.assigmentday4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.assigmentday4.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        AppPreferences.init(this)
        setupViewPager()
        setupTabLayout()
    }

    private fun setupViewPager() {
        val adapter = ViewPagerAdapter(this, 2)
        binding.viewPager.adapter = adapter
    }

    private fun setupTabLayout() {
        TabLayoutMediator(
            binding.tabLayout, binding.viewPager
        ) { tab, position ->
            if (position == 0) {
                tab.text = "Dialog"
            } else tab.text = "Setting"
        }.attach()
    }

    override fun onBackPressed() {
        val viewPager = binding.viewPager
        if (viewPager.currentItem == 0) {

            super.onBackPressed()
        } else {
            viewPager.currentItem = viewPager.currentItem - 1
        }
    }

}