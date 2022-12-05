package com.example.tekotest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.tekotest.data.models.ProductUpdate
import com.example.tekotest.databinding.ItemShowProductBinding
import com.example.tekotest.utils.BaseDiffUtilCallBack


class ShowProductAdapter() :
    RecyclerView.Adapter<ShowProductAdapter.ViewHolder>() {

    private var products = arrayListOf<ProductUpdate>()

    inner class ViewHolder(private val binding: ItemShowProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(p: ProductUpdate) {
            binding.apply {
                product = p
                executePendingBindings()
            }
        }

    }

    fun submitData(temp: List<ProductUpdate>) {
        val diff = DiffUtil.calculateDiff(BaseDiffUtilCallBack(products, temp) { io, ine ->
            products[io].id == temp[ine].id
        })
        products.clear()
        products.addAll(temp)
        diff.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemShowProductBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        products[position].let {
            holder.bind(it)
        }
    }

    override fun getItemCount() = products.size
}