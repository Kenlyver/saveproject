package com.example.clientstudent.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.clientstudent.databinding.FragmentStudentBinding
import com.example.clientstudent.view.adpter.StudentAdapter
import com.example.clientstudent.viewmodel.MainViewModel
import com.example.serverstudent.entity.Student
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class StudentFragment : Fragment() {
    @Inject
    lateinit var studentAdapter: StudentAdapter
    private var listStudent: List<Student> = listOf()
    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var binding: FragmentStudentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStudentBinding.inflate(layoutInflater)
        viewModel.students.observe(viewLifecycleOwner) {
            listStudent = it
            studentAdapter.submitData(it)
            Log.d("test", it.toString())
        }
        binding.recyclerStudent.adapter = studentAdapter
        binding.recyclerStudent.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        return binding.root
    }
}