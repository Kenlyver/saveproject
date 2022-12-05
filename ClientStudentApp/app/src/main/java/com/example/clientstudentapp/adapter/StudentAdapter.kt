package com.example.clientstudent.view.adpter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.clientstudentapp.databinding.ItemStudentBinding
import com.example.serverstudentapp.model.Student

class StudentDiff(val oldStudent: List<Student>, val newStudent: List<Student>) :
    DiffUtil.Callback() {
    override fun getOldListSize() = oldStudent.size

    override fun getNewListSize() = newStudent.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldStudent[oldItemPosition].id == newStudent[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldStudent === newStudent
    }
}

class StudentAdapter : RecyclerView.Adapter<StudentAdapter.ViewHolder>() {
    private var student = arrayListOf<Student>()
    private lateinit var mListener: onItemClickListener

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener) {
        mListener = listener
    }

    fun submitData(temp: List<Student>) {
        val diff = DiffUtil.calculateDiff(StudentDiff(student, temp))
        student.clear()
        student.addAll(temp)
        diff.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ItemStudentBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            mListener
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(student[position])
    }

    inner class ViewHolder(private val binding: ItemStudentBinding, listener: onItemClickListener) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(s: Student) {
            binding.student = s
        }

        init {
            binding.root.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }

    override fun getItemCount() = student.size

}