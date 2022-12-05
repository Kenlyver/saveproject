package com.example.clientstudentapp.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.clientstudentapp.databinding.FragmentInsertBinding
import com.example.serverstudentapp.model.Student

class InsertFragment : Fragment() {
    private lateinit var binding: FragmentInsertBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInsertBinding.inflate(layoutInflater)
        setHasOptionsMenu(true)
        binding.btnInsert.setOnClickListener {
            addStudent()
        }
        return binding.root
    }

    private fun addStudent() {
        binding.apply {
            val std = Student(
                name = edtName.text.toString(),
                age = edtAge.text.toString().toInt(),
                mathP = edtPointMath.text.toString().toFloat(),
                physicP = edtPointPhysic.text.toString().toFloat(),
                englishP = edtPointEnglish.text.toString().toFloat(),
            )
            findNavController().previousBackStackEntry?.savedStateHandle?.set("Insert", std)
            findNavController().popBackStack()
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        menu.clear()
    }
}