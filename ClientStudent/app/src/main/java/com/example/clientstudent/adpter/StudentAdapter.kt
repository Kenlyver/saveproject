package com.example.clientstudent.view.adpter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.clientstudent.databinding.ItemStudentBinding
import com.example.serverstudent.entity.Student

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
    private var student= arrayListOf<Student>()
    private var listStudent = mutableListOf<Student>()
    private var itemSetOnClickListener: ((student: Student) -> Unit)? = null

    fun setOnItemClickListener(itemOnClick: (student: Student) -> Unit) {
        itemSetOnClickListener = itemOnClick
    }

    fun submitData(temp: List<Student>) {
        val diff = DiffUtil.calculateDiff(StudentDiff(student, temp))
        student.clear()
        student.addAll(temp)
        diff.dispatchUpdatesTo(this)
    }
    fun updateData(temp: List<Student>) {
        temp.let { new ->
            val diffResult: DiffUtil.DiffResult = StudentDiff(student, new).let { diff ->
                DiffUtil.calculateDiff(diff)
            }
            student.clear()
            student.addAll(new)
            diffResult.dispatchUpdatesTo(this)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ItemStudentBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(student[position])
    }

    inner class ViewHolder(private val binding:ItemStudentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(s: Student) {
            binding.student = s
        }
    }

    override fun getItemCount() = student.size

}