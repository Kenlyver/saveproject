package com.example.assignmentday6t3.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.assignmentday6t3.databinding.FragmentSignInBinding
import com.example.assignmentday6t3.viewModel.MainViewModel

class SignInFragment : Fragment() {
    lateinit var binding: FragmentSignInBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignInBinding.inflate(layoutInflater)
        binding.signin = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        binding.lifecycleOwner = this
        return binding.root
    }
}