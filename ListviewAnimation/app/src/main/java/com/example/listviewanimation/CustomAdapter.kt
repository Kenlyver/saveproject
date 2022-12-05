package com.example.listviewanimation

import com.example.listviewanimation.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.listviewanimation.databinding.CardViewBinding


class CustomAdapter(
    private val onItemClick: (Song, Int) -> Unit
) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    private val songs= mutableListOf<Song>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        CardViewBinding.inflate(LayoutInflater.from(parent.context), parent, false), onItemClick
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(songs[position])
    }


    override fun getItemCount():Int{
        return songs.size
    }

    fun setData(data:List<Song>){
        songs.clear()
        songs.addAll(data)
        notifyDataSetChanged()
    }



    class ViewHolder(
        private val binding: CardViewBinding,
        private val onItemClick: (Song,Int) -> Unit

    ):RecyclerView.ViewHolder(binding.root){
        private var lastPosition = -1
        fun bindData(song:Song){
            binding.apply {
                txtSongName.text = song.Name
                imgDelete.setOnClickListener {
                    onItemClick(song,position)
                }
            }
            val activity=binding.root.getContext() as AppCompatActivity
            val animation = AnimationUtils.loadAnimation(
                activity,
                if (position > lastPosition) R.anim.up_from_bottom else R.anim.down_from_top
            )
            binding.root.startAnimation(animation)
            lastPosition = position
        }
    }

}