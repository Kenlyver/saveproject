package com.example.jetpackday1.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.jetpackday1.databinding.FragmentCountBinding
import com.example.jetpackday1.viewmodel.MainViewModel
import com.example.jetpackday1.viewmodel.ParametterViewModel
import com.example.jetpackday1.viewmodel.ParametterViewModelFactory

class OneFragment : Fragment() {
    private lateinit var binding: FragmentCountBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var pViewModel: ParametterViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
//        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        pViewModel = ViewModelProvider(
            this,
            ParametterViewModelFactory(requireActivity().application, 10)
        ).get(ParametterViewModel::class.java)
        viewModel.number.observe(viewLifecycleOwner, {
            binding.textView.text = it.toString()
        })
        viewModel.needToast.observe(viewLifecycleOwner,{
            if(it){
                Toast.makeText(requireContext(),"Toast",Toast.LENGTH_SHORT).show()
            }
        })
        binding = FragmentCountBinding.inflate(layoutInflater)
        binding.button.setOnClickListener {
            viewModel.upNumber()
            viewModel.showToast()
        }
        return binding.root
    }
}