package com.example.tekotest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.tekotest.data.models.Product
import com.example.tekotest.databinding.ItemProductBinding
import com.example.tekotest.utils.BaseDiffUtilCallBack
import com.example.tekotest.utils.callback.ItemClick


class ProductAdapter(private val itemClick: ItemClick<Product>) :
    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    private var products = arrayListOf<Product>()

    inner class ViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(p: Product) {
            binding.apply {
                product = p
                itemClick = this@ProductAdapter.itemClick
                executePendingBindings()
            }
        }

    }

    fun submitData(temp: List<Product>) {
        val diff = DiffUtil.calculateDiff(BaseDiffUtilCallBack(products, temp) { io, ine ->
            products[io].id == temp[ine].id
        })
        products.clear()
        products.addAll(temp)
        diff.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemProductBinding.inflate(
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