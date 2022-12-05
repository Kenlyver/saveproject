package com.example.clientstudentapp.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.clientstudentapp.databinding.FragmentUpdateBinding
import com.example.serverstudentapp.model.Student

class UpdateFragment : Fragment() {
    private lateinit var binding: FragmentUpdateBinding
    private val args:UpdateFragmentArgs by navArgs()
    private lateinit var student: Student
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUpdateBinding.inflate(layoutInflater)
        if(!(args.student == null)){
            binding.apply {
                edtName.setText(args.student.name)
                edtAge.setText(args.student.age.toString())
                edtPointMath.setText(args.student.mathP.toString())
                edtPointPhysic.setText(args.student.physicP.toString())
                edtPointEnglish.setText(args.student.englishP.toString())
            }
            student = args.student
            binding.student = student
        }
        binding.btnUpdate.setOnClickListener {
            updateStudent()
        }
        return binding.root
    }
    private fun updateStudent()
    {
        binding.apply {
            val std = student?.copy(
                name = edtName.text.toString(),
                age = edtAge.text.toString().toInt(),
                mathP = edtPointMath.text.toString().toFloat(),
                physicP = edtPointPhysic.text.toString().toFloat(),
                englishP = edtPointEnglish.text.toString().toFloat(),
            )
            findNavController().previousBackStackEntry?.savedStateHandle?.set("Update", std)
            findNavController().popBackStack()
        }
    }
}