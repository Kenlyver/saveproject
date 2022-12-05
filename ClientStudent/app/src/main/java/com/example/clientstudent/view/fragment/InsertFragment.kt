package com.example.clientstudent.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.clientstudent.databinding.FragmentInsertBinding
import com.example.clientstudent.viewmodel.MainViewModel
import com.example.serverstudent.entity.Student
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InsertFragment : Fragment() {
    private lateinit var binding: FragmentInsertBinding
    private val viewModel: MainViewModel by viewModels()
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

    override fun onPrepareOptionsMenu(menu: Menu) {
        menu.clear()
    }

    private fun addStudent() {
        binding.apply {
            val name = edtName.text.toString()
            val age = edtAge.text.toString()
            val mathP = edtPointMath.text.toString()
            val physicP = edtPointPhysic.text.toString()
            val englishP = edtPointPhysic.text.toString()

            viewModel.insertStudent(
                Student(
                    null,
                    name = name,
                    age = age.toInt(),
                    mathP = mathP,
                    physicP = physicP,
                    englishP = englishP
                )
            )
        }

    }
}