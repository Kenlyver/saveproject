package com.example.serverorder.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.serverorder.databinding.OrderItemBinding
import com.example.serverorder.model.ConnectTable

class OrderDiff(val oldOrder: List<ConnectTable>, val newOrder: List<ConnectTable>) :
    DiffUtil.Callback() {
    override fun getOldListSize() = oldOrder.size

    override fun getNewListSize() = newOrder.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldOrder[oldItemPosition].order == newOrder[newItemPosition].order
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldOrder === newOrder
    }

}

class OrderAdapter : RecyclerView.Adapter<OrderAdapter.ViewHolder>() {
    private val orderList = arrayListOf<ConnectTable>()
    private lateinit var mListener: onItemClickListener

    class ViewHolder(private val binding: OrderItemBinding, listener: onItemClickListener) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(order: ConnectTable) {
            binding.order = order
            binding.executePendingBindings()
        }

        init {
            binding.root.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }

    fun submitData(temp: List<ConnectTable>) {
        val diff = DiffUtil.calculateDiff(OrderDiff(orderList, temp))
        orderList.clear()
        orderList.addAll(temp)
        diff.dispatchUpdatesTo(this)
    }

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener) {
        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            OrderItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ), mListener
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        orderList[position].let {
            holder.bind(it)
        }
    }

    override fun getItemCount() = orderList.size

}

