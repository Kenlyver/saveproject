package com.example.jetpackday1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jetpackday1.adapter.StudentAdapter
import com.example.jetpackday1.adapter.StudentPagingAdapter
import com.example.jetpackday1.databinding.ActivityStudentBinding
import com.example.jetpackday1.db.Student
import com.example.jetpackday1.viewmodel.StudentViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class StudentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStudentBinding
    private val viewModel: StudentViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        val adapter = StudentAdapter()

//        viewModel.students.observe(this, {
//            adapter.submitData(it)
//        })
        val adapter = StudentPagingAdapter()
        lifecycleScope.launchWhenStarted {
            viewModel.studentPage.collectLatest {
                adapter.submitData(it)
            }
        }
        binding.rvStudent.adapter = adapter
        binding.rvStudent.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        binding.btnAdd.setOnClickListener {
            val name = binding.edtName.text.toString()
            val className = binding.edtClass.text.toString()
            val address = binding.edtAddress.text.toString()
            val score = binding.edtScore.text.toString()
            val student = Student(
                null,
                name = name,
                className = className,
                address = address,
                score = score.toFloat()
            )
            viewModel.insertStudent(student)
        }
        binding.btnUpdate.setOnClickListener {
            val id = binding.tvId.text.toString()
            val name = binding.edtName.text.toString()
            val className = binding.edtClass.text.toString()
            val address = binding.edtAddress.text.toString()
            val score = binding.edtScore.text.toString()
            val student = Student(
                id = id.toInt(),
                name = name,
                className = className,
                address = address,
                score = score.toFloat()
            )
            viewModel.updateStudent(student)
        }
        binding.btnDelete.setOnClickListener {
            val id = binding.tvId.text.toString()
            viewModel.deleteStudentById(id.toInt())
        }
    }
}