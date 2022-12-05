package com.example.assignmentday5.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assignmentday5.ShowFragment
import com.example.assignmentday5.data.user
import com.example.assignmentday5.databinding.ParentListBinding

class CustomAdapter(
) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
    private var users = mutableListOf<user>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ParentListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    fun setData(data: List<user>) {
        users.clear()
        users.addAll(data)
        notifyDataSetChanged()
    }

    fun filterList(filterllist: ArrayList<user>) {
        users = filterllist
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(users[position])
    }

    override fun getItemCount(): Int {
        return users.size
    }


    class ViewHolder(
        private val binding: ParentListBinding

    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(users: user) {
            binding.apply {
                txtName.text = users.Name
                txtAge.text = users.Age.toString()
                if (users.Sex == 0) {
                    txtSex.text = "Female"
                } else txtSex.text = "Male"
                val activity = binding.root.getContext() as AppCompatActivity
                var check = false
                var count = 0
                val childAdapter = InfoAdapter()
                val data = ShowFragment().getDataInfo()
                root.setOnClickListener {
                    binding.parentList.visibility = View.VISIBLE
                    count += 1
                    if (count > 1) {
                        check = false
                    } else check = true
                    if (check) {
                        binding.parentList.layoutManager = LinearLayoutManager(activity)
                        binding.parentList.adapter = childAdapter
                        childAdapter.setData(data)
                    }
                    if (count == 2) {
                        binding.parentList.visibility = View.GONE
                        childAdapter.clearData()
                        count = 0
                    }
                }
            }

        }
    }
}