package com.example.tekotest.utils.dialog

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tekotest.adapter.ShowProductAdapter
import com.example.tekotest.data.models.ProductUpdate
import com.example.tekotest.databinding.ShowProductDialogBinding
import com.example.tekotest.viewmodel.ProductViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class DialogShowProduct :
    DialogFragment() {
    private lateinit var binding: ShowProductDialogBinding
    private lateinit var products: List<ProductUpdate>
    private val viewModel: ProductViewModel by activityViewModels()
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = ShowProductDialogBinding.inflate(layoutInflater)
        val adapter = ShowProductAdapter()
        products.let {
            adapter.submitData(it)
            Log.d("test", it.toString())
        }
        binding.apply {
            rvProduct.adapter = adapter
            rvProduct.layoutManager =
                LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
            tvConfirm.setOnClickListener {
                dismiss()
                viewModel.deleteAllUpdate()
            }
        }
        return MaterialAlertDialogBuilder(requireContext()).setView(binding.root).create()
    }

    fun showDialog(childFragment: FragmentManager, product: List<ProductUpdate>) {
        show(childFragment, "Show")
        this.products = product
    }
}