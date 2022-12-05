package com.example.jetpackday1.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.jetpackday1.R
import com.example.jetpackday1.databinding.FragmentHomeBinding

class HomeFragment: Fragment() {
    lateinit var binding:FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        binding.buttonLogin.setOnClickListener {
//            val action = HomeFragmentDirections.
            findNavController().navigate(R.id.action_homeFragment_to_loginFragment)
        }
        return binding.root
    }
}
