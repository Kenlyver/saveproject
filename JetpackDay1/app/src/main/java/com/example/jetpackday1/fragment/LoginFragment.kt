package com.example.jetpackday1.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.jetpackday1.databinding.FragmentLoginBinding

class LoginFragment: Fragment() {
    lateinit var binding:FragmentLoginBinding
//    private var args:LoginFragment by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(layoutInflater)
//        binding.edtUsername.setText(args.username)
        binding.btnConfirm.setOnClickListener {
            val user= binding.edtUsername.text.toString()
            val password= binding.edtPassword.text.toString()
//            val action =
        }
        return binding.root
    }
}