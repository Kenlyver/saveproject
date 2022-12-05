package com.example.assignmentday5.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.assignmentday5.data.Info
import com.example.assignmentday5.databinding.ChildListBinding
import kotlin.random.Random

class InfoAdapter() : RecyclerView.Adapter<InfoAdapter.ViewHolder>() {
    private val infoUser = mutableListOf<Info>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ChildListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    fun setData(data: List<Info>) {
        infoUser.clear()
        infoUser.addAll(data)
        notifyDataSetChanged()
    }

    fun clearData() {
        infoUser.clear()
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(infoUser[position])
    }

    override fun getItemCount(): Int {
        return infoUser.size
    }

    class ViewHolder(private val binding: ChildListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(infos: Info) {
            binding.apply {
                txtSubject.text = infos.Subject
                txtExercise.text = infos.Exersice
                txtDate.text = infos.Date
                txtTime.text = infos.Time
                txtDateLeft.text = infos.DayLeft
            }
        }
    }
}