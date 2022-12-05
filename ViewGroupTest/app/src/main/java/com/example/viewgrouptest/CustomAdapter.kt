package com.example.viewgrouptest

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.viewgrouptest.databinding.CardTemplateBinding

class CustomAdapter(
    private val onItemClick: (Int) -> Unit
) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
    private val users= mutableListOf<Users>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
          CardTemplateBinding.inflate(LayoutInflater.from(parent.context), parent, false),
        onItemClick
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(users[position])
    }

    override fun getItemCount():Int{
      return users.size
    }

    fun setData(data:List<Users>){
        users.clear()
        users.addAll(data)
        notifyDataSetChanged()
    }

    class ViewHolder(
        private val binding: CardTemplateBinding,
        private val onButtonClick:(Int)->Unit
    ):RecyclerView.ViewHolder(binding.root){
        fun bindData(user:Users){
            binding.apply {
                txtFirstName.text = user.firstName
                txtLastName.text = user.lastName
                txtUserName.text = user.userName
                txtAge.text = user.age.toString()
                btnViewDetail.setOnClickListener {
                        v:View->Unit
                    val intent =Intent(v.context,DetailScreen::class.java)
                    intent.putExtra("firstName",user.firstName)
                    intent.putExtra("lastName",user.lastName)
                    intent.putExtra("userName",user.userName)
                    intent.putExtra("Age",user.age.toString())
                    v.context.startActivity(intent)
                    onButtonClick(adapterPosition)
                }
            }
        }
    }
}