package com.example.jetpackday1.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.jetpackday1.fragment.OneFragment
import com.example.jetpackday1.fragment.TwoFragment

class MainPagerAdapter(activity:AppCompatActivity):FragmentStateAdapter(activity) {
    override fun getItemCount()=2
    override fun createFragment(position: Int): Fragment {
        return when(position){
            0->OneFragment()
            1->TwoFragment()
            else->throw IllegalArgumentException("Unknown: $position")
        }
    }
}