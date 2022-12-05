package com.example.teamanagement.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.teamanagement.databinding.CustomViewBinding
import com.example.teamanagement.model.Tea

class TeaDiff(
    val oldTea: List<Tea>,
    val newTea: List<Tea>
) : DiffUtil.Callback() {
    override fun getOldListSize() = oldTea.size

    override fun getNewListSize() = newTea.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldTea[oldItemPosition].name == newTea[newItemPosition].name
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldTea === newTea
    }

}

class TeaAdapter() : RecyclerView.Adapter<TeaAdapter.ViewHolder>() {
    private val tea = arrayListOf<Tea>()
    private lateinit var mListener: onItemClickListener

    class ViewHolder(private val binding: CustomViewBinding, listener: onItemClickListener) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(t: Tea) {
            binding.tea = t
            binding.executePendingBindings()
        }

        init {
            binding.root.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }

    fun submitData(temp: List<Tea>) {
        val diff = DiffUtil.calculateDiff(TeaDiff(tea, temp))
        tea.clear()
        tea.addAll(temp)
        diff.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            CustomViewBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ), mListener
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        tea[position].let {
            holder.bind(it)
        }
    }

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener) {
        mListener = listener
    }

    override fun getItemCount() = tea.size
}