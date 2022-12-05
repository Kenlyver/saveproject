package com.example.assignmentday6t3.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.assignmentday6t3.Interface.LoginResultCallBack
import com.example.assignmentday6t3.R
import com.example.assignmentday6t3.databinding.FragmentSignUpBinding
import com.example.assignmentday6t3.viewModel.MainViewModel
import com.example.assignmentday6t3.viewModel.RegisterViewModelFactory

class SignUpFragment : Fragment(), LoginResultCallBack {
    lateinit var binding: FragmentSignUpBinding
    private lateinit var viewModel: MainViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignUpBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(
            requireActivity(),
            RegisterViewModelFactory(this)
        ).get(MainViewModel::class.java)
        binding.signup = viewModel
        viewModel.errorToast.observe(viewLifecycleOwner, Observer {
            if (it) {
                viewModel.doneToast()
            }
        })
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onSuccess(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        val fr = getFragmentManager()?.beginTransaction()
        fr?.replace(R.id.frameLayout, SignInFragment())
        fr?.commit()
    }

    override fun onError(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}