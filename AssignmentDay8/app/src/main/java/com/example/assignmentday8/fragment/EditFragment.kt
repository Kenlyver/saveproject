package com.example.assignmentday8.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.assignmentday8.databinding.FragmentEditBinding
import com.example.assignmentday8.model.dbCake.Cake
import com.example.assignmentday8.viewModel.CakeViewModel


class EditFragment : Fragment() {
    private lateinit var binding:FragmentEditBinding
    private val viewModel:CakeViewModel by viewModels()
    private var amountChange:Int = 0
    private var nowAmount:Int = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditBinding.inflate(layoutInflater)
        viewModel.cake.observe(viewLifecycleOwner, Observer {
            nowAmount = it.Amount
            val price = it.Price.toString()
            binding.txtNumberOfCake.setText(nowAmount.toString())
            binding.edtPriceCake.setText(price)
        })
        binding.btnAdd.setOnClickListener {
            viewModel.upCake()
            viewModel.numberCake.observe(viewLifecycleOwner, Observer {
                binding.edtSaleCake.setText(it.toString())
            })
        }
        binding.btnMinus.setOnClickListener {
            viewModel.downCake()
            viewModel.numberCake.observe(viewLifecycleOwner, Observer {
                binding.edtSaleCake.setText(it.toString())
            })
        }
        binding.btnApply.setOnClickListener {
            val id = 1
            val amountStr = binding.edtSaleCake.text.toString()
            amountChange = amountStr.toInt()
            nowAmount = nowAmount + amountChange
            val price = binding.edtPriceCake.text.toString()
            val cake = Cake(Id = id,Amount = nowAmount, Price = price)
            viewModel.resetCake()
            viewModel.updateCake(cake)
        }
        return binding.root
    }
}