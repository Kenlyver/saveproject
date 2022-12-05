package com.example.jetpackday1.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.jetpackday1.databinding.FragmentTermsBinding

class TermsFragment: Fragment() {
    lateinit var binding: FragmentTermsBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTermsBinding.inflate(layoutInflater)
        return binding.root
    }
}