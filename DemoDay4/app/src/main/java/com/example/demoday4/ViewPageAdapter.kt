package com.example.demoday4

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.demoday4.databinding.ItemPageBinding

class ViewPageAdapter : RecyclerView.Adapter<PagerVH>() {

    //array of colors to change the background color of screen
    private val colors = intArrayOf(
        android.R.color.black,
        android.R.color.holo_red_light,
        android.R.color.holo_blue_dark,
        android.R.color.holo_purple
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PagerVH(
        ItemPageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )


    override fun getItemCount(): Int = Int.MAX_VALUE


    override fun onBindViewHolder(holder: PagerVH, position: Int) {

    }
}

class PagerVH(binding: ItemPageBinding) : RecyclerView.ViewHolder(binding.root)