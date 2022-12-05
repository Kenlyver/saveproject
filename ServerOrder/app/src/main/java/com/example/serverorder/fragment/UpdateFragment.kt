package com.example.serverorder.fragment

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.serverorder.databinding.FragmentUpdateBinding
import com.example.serverorder.model.Order
import com.example.serverorder.viewModel.AddViewModel

class UpdateFragment : Fragment() {
    private lateinit var binding: FragmentUpdateBinding
    private lateinit var autoCompleteTextView: AutoCompleteTextView
    private val args: UpdateFragmentArgs by navArgs()
    private val viewModel: AddViewModel by viewModels()
    private lateinit var orderList: Order
    private var idCustomer: Int = -1
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUpdateBinding.inflate(layoutInflater)
        if (!(args.data == null)) {
            binding.layoutName.editText?.setText(args.data.customer?.name.toString())
            binding.txtDate.setText(args.data.order?.timestamp)
            binding.edtValue.setText(args.data.order?.value.toString())
        }
        orderList = args.data.order!!
        binding.order = orderList
        autoCompleteTextView = binding.layoutName.editText as AutoCompleteTextView
        binding.layoutName.apply {
            autoCompleteTextView.onItemClickListener =
                AdapterView.OnItemClickListener { _, _, p2, _ ->
                    idCustomer = viewModel.getCustomer.value?.get(p2)!!.id!!
                    autoCompleteTextView.setText(viewModel.getCustomer.value?.get(p2)!!.name, false)
                }

        }
        viewModel.getCustomer.observe(viewLifecycleOwner) {
            val adapterCustomer = ArrayAdapter(
                requireContext(),
                R.layout.simple_list_item_1,
                it
            )
            autoCompleteTextView.setAdapter(adapterCustomer)
        }
        binding.btnUpdate.setOnClickListener {
            updateOrder()
        }
        return binding.root
    }

    private fun updateOrder() {
        binding.apply {
            val order = orderList?.copy(
                idCustomer = idCustomer,
                timestamp = binding.txtDate.text.toString(),
                value = binding.edtValue.text.toString().toInt()
            )
            findNavController().previousBackStackEntry?.savedStateHandle?.set("Update", order)
            findNavController().popBackStack()
        }
    }
}