package com.example.clientorder.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.clientorder.databinding.ItemReportBinding
import com.example.clientorder.model.Report


class OrderDiff(val oldOrder: List<Report>, val newOrder: List<Report>) :
    DiffUtil.Callback() {
    override fun getOldListSize() = oldOrder.size

    override fun getNewListSize() = newOrder.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldOrder[oldItemPosition].date == newOrder[newItemPosition].date
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldOrder === newOrder
    }

}

class ReportAdapter : RecyclerView.Adapter<ReportAdapter.ViewHolder>() {
    private val orderList = arrayListOf<Report>()

    class ViewHolder(private val binding: ItemReportBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(report: Report) {
            binding.report = report
            binding.executePendingBindings()
        }
    }

    fun submitData(temp: List<Report>) {
        val diff = DiffUtil.calculateDiff(OrderDiff(orderList, temp))
        orderList.clear()
        orderList.addAll(temp)
        diff.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemReportBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        orderList[position].let {
            holder.bind(it)
        }
    }

    override fun getItemCount() = orderList.size

}

