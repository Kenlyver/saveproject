package com.example.serverorder.fragment

import android.app.AlertDialog
import android.app.DatePickerDialog
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
import com.example.serverorder.databinding.CustomDialogBinding
import com.example.serverorder.databinding.FragmentAddBinding
import com.example.serverorder.model.Customer
import com.example.serverorder.model.Order
import com.example.serverorder.viewModel.AddViewModel
import java.util.*

class AddFragment : Fragment() {
    private lateinit var binding: FragmentAddBinding
    private lateinit var autoCompleteTextView: AutoCompleteTextView
    private val viewModel: AddViewModel by viewModels()
    private var idCustomer: Int = -1
    lateinit var bindingDialog: CustomDialogBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddBinding.inflate(layoutInflater)
        autoCompleteTextView = binding.layoutName.editText as AutoCompleteTextView
        binding.layoutName.apply {
            autoCompleteTextView.onItemClickListener =
                AdapterView.OnItemClickListener { _, _, p2, _ ->
                    idCustomer = viewModel.getCustomer.value?.get(p2)!!.id!!
                    autoCompleteTextView.setText(viewModel.getCustomer.value?.get(p2)!!.name, false)
                }

        }
        binding.btnInsertCustomer.setOnClickListener {
            val builder = AlertDialog.Builder(context)
            val inflater = layoutInflater
            builder.apply {
                setTitle("Name")
                bindingDialog = CustomDialogBinding.inflate(layoutInflater, binding.root, false)
                setView(bindingDialog.root)
                setPositiveButton("OK") { dialogInterface, i ->
                    val customer = Customer(
                        null,
                        name = bindingDialog.editName.text.toString()
                    )
                    viewModel.addCustomer(customer)
                }
                show()
            }
        }
        binding.txtDate.setOnClickListener {
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            var month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)
            val dpd = DatePickerDialog(
                requireContext(),
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    month += 1
                    viewModel.date.postValue("$dayOfMonth/$month/$year")
                    viewModel.date.observe(viewLifecycleOwner) {
                        binding.txtDate.setText(it)
                    }
                },
                year,
                month,
                day
            )
            dpd.show()
        }
        viewModel.getCustomer.observe(viewLifecycleOwner) {
            val adapterCustomer = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_list_item_1,
                it
            )
            autoCompleteTextView.setAdapter(adapterCustomer)
            if (idCustomer == -1) {
                idCustomer = it[0].id!!
                autoCompleteTextView.setText(it[0].name, false)
            } else {
                autoCompleteTextView.setText(it[idCustomer].name, false)
            }
        }
        binding.btnInsert.setOnClickListener {
            addOrder()
        }
        return binding.root
    }

    private fun addOrder() {
        binding.apply {
            val order = Order(
                idCustomer = idCustomer,
                timestamp = binding.txtDate.text.toString(),
                value = binding.edtValue.text.toString().toInt()
            )
            findNavController().previousBackStackEntry?.savedStateHandle?.set("Insert", order)
            findNavController().popBackStack()
        }
    }
}