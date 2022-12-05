package com.example.fragmentdemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.fragmentdemo.databinding.CardviewBinding


class CustomAdapter(
    private val onItemClick: (Users, Int) -> Unit
) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {


    private val users = mutableListOf<Users>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        CardviewBinding.inflate(LayoutInflater.from(parent.context), parent, false), onItemClick
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(users[position])
        holder.itemView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {

            }
        })
    }


    override fun getItemCount(): Int {
        return users.size
    }

    fun setData(data: List<Users>) {
        users.clear()
        users.addAll(data)
        notifyDataSetChanged()
    }

    class ViewHolder(
        private val binding: CardviewBinding,
        private val onItemClick: (Users, Int) -> Unit

    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(user: Users) {
            binding.apply {
                txtFullName.text = user.FullName
                txtFullName.setOnClickListener {
                    onItemClick(user, position)
                    val bundle = Bundle()
                    bundle.putString("FullName", user.FullName)
                    bundle.putString("NumberAccount", user.NumberAccount)
                    bundle.putString("Date", user.Date)
                    val activity = binding.root.getContext() as AppCompatActivity
                    val detailFragment = DetailFragment()
                    detailFragment.setArguments(bundle)
                    activity.supportFragmentManager.beginTransaction()
                        .replace(R.id.listFragment, detailFragment).addToBackStack(null).commit()
                }
            }
        }
    }
}