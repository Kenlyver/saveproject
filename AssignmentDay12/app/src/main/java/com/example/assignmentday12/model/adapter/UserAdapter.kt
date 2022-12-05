package com.example.assignmentday12.model.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.assignmentday12.databinding.CustomUserBinding
import com.example.assignmentday12.view.model.User

class UserDiff(val oldUser: List<User>, val newUser: List<User>) : DiffUtil.Callback() {
    override fun getOldListSize() = oldUser.size

    override fun getNewListSize() = newUser.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldUser[oldItemPosition].id == newUser[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldUser === newUser
    }

}

class UserAdapter() : RecyclerView.Adapter<UserAdapter.ViewHolder>() {
    private var user = arrayListOf<User>()

    class ViewHolder(private val binding: CustomUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(u: User) {
            binding.user = u
            binding.executePendingBindings()
        }
    }

    fun filterList(filterllist: ArrayList<User>) {
        val diff = DiffUtil.calculateDiff(UserDiff(user, filterllist))
        user.clear()
        user.addAll(filterllist)
        diff.dispatchUpdatesTo(this)
    }

    fun submitData(temp: List<User>) {
        val diff = DiffUtil.calculateDiff(UserDiff(user, temp))
        user.clear()
        user.addAll(temp)
        diff.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            CustomUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        user[position].let {
            holder.bind(it)
        }
    }

    override fun getItemCount() = user.size
}