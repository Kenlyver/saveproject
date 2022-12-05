package com.example.jetpackday1.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.AndroidViewModel
import com.example.jetpackday1.databinding.FragmentCountBinding
import com.example.jetpackday1.viewmodel.MainViewModel

class TwoFragment: Fragment() {
    private lateinit var binding: FragmentCountBinding
//    private val viewModel:MainViewModel by viewModels()
    private val viewModel:MainViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCountBinding.inflate(layoutInflater)
        viewModel.number.observe(viewLifecycleOwner,{
            binding.textView.text = it.toString()
        })
        binding.button.setOnClickListener {
            viewModel.upNumber()
        }
        return binding.root
    }
}