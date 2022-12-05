package com.example.jetpackday1.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.jetpackday1.databinding.ItemStudentBinding
import com.example.jetpackday1.db.Student

class StudentDiff(
    val oldStudent:List<Student>,
    val newStudent: List<Student>
):DiffUtil.Callback(){
    override fun getOldListSize()=oldStudent.size

    override fun getNewListSize()=newStudent.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldStudent[oldItemPosition].id == newStudent[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldStudent === newStudent
    }

}

class StudentAdapter(

):RecyclerView.Adapter<StudentAdapter.ViewHolder>() {
    private val students = arrayListOf<Student>()

    fun submitData(temp:List<Student>){
        val diff = DiffUtil.calculateDiff(StudentDiff(students,temp))
        students.clear()
        students.addAll(temp)
        diff.dispatchUpdatesTo(this)
    }
    class ViewHolder(private val binding:ItemStudentBinding):
        RecyclerView.ViewHolder(binding.root){
            fun bind(s:Student){
                binding.s = s
                binding.executePendingBindings()//bắt buộc
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemStudentBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        students[position].let{
            holder.bind(it)
        }
    }

    override fun getItemCount() = students.size
}